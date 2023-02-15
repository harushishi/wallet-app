package com.harushishi.walletapp.User;

import com.harushishi.walletapp.Config.JwtService;
import com.harushishi.walletapp.Currency.CurrencyRepository;
import com.harushishi.walletapp.ErrorHandling.BadRequestException;
import com.harushishi.walletapp.ErrorHandling.NotFoundException;
import com.harushishi.walletapp.ErrorHandling.UnprocessableEntityException;
import com.harushishi.walletapp.Wallet.Wallet;
import com.harushishi.walletapp.Wallet.WalletRepository;
import com.harushishi.walletapp.WalletCurrency.ConversionDTO;
import com.harushishi.walletapp.WalletCurrency.ExchangeDTO;
import com.harushishi.walletapp.WalletCurrency.WalletCurrency;
import com.harushishi.walletapp.WalletCurrency.WalletCurrencyRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository repository;
  private final WalletRepository wRepository;
  private final CurrencyRepository cRepository;
  private final WalletCurrencyRepository wcRepository;
  private final JwtService jwtService;
  private final RestTemplate restTemplate;
  @Qualifier("getCONVERSION_BASEURL")
  @Autowired
  private String CONVERSION_BASEURL;

  public UserDTO getUser(String token) {

    Claims userInfo = jwtService.extractAllClaims(token);

    UserDTO user = repository.getUserById(Long.valueOf(userInfo.getId()));

    List<WalletCurrency> currencies = wRepository.getUserCurrenciesById(Long.valueOf(userInfo.getId()));

    user.setWallet_currencies(currencies);

    return user;
  }

  public WalletCurrency deposit(String token, String currency, Double quantity) {

    Claims info = jwtService.extractAllClaims(token);
    UserDTO user = repository.getUserById(Long.valueOf(info.getId()));
    Wallet wallet = wRepository.getWalletById(user.getWallet_id());
    WalletCurrency wc = wcRepository.findByWalletAndCurrency_ShortName(wallet, currency.toUpperCase());

    if (quantity < 100) {
      throw new BadRequestException("Quantity should be a minimum of 100");
    }

    if (wc == null) {
      // user does not have this currency yet, but it is a valid one.
      if (cRepository.findByShortName(currency.toUpperCase()) != null) {
        var new_currency = WalletCurrency.builder()
            .currency(cRepository.findByShortName(currency.toUpperCase()))
            .quantity(quantity)
            .wallet(wallet)
            .build();

        List<WalletCurrency> walletCurrencies = wallet.getWallet_currencies();

        walletCurrencies.add(new_currency);
        wRepository.save(wallet);
        return wallet.getWallet_currencies().get(walletCurrencies.size() - 1);

      } else if (cRepository.findByShortName(currency.toUpperCase()) == null) {
        throw new BadRequestException("Invalid currency");
      }
    }

    assert wc != null;
    wc.setQuantity(wc.getQuantity() + quantity);

    wcRepository.save(wc);

    return wc;
  }

  public ExchangeDTO exchange(String token, String from, String to, Double quantity) {
    Double rate = calculateRate(from, to);

    Claims info = jwtService.extractAllClaims(token);
    UserDTO user = repository.getUserById(Long.valueOf(info.getId()));
    Wallet wallet = wRepository.getWalletById(user.getWallet_id());
    WalletCurrency fromCurrency = wcRepository.findByWalletAndCurrency_ShortName(wallet, from.toUpperCase());
    WalletCurrency toCurrency = wcRepository.findByWalletAndCurrency_ShortName(wallet, to.toUpperCase());

    // if user didn't have any of the currencies in his list, now he will.
    if (fromCurrency == null) {
      addCurrency(wallet, from);
      fromCurrency = wcRepository.findByWalletAndCurrency_ShortName(wallet, from.toUpperCase());
      wallet = wRepository.getWalletById(user.getWallet_id());
    }

    if (toCurrency == null) {
      addCurrency(wallet, to);
      toCurrency = wcRepository.findByWalletAndCurrency_ShortName(wallet, to.toUpperCase());
    }

    if (fromCurrency.getQuantity() < quantity) {
      throw new BadRequestException("Insufficient funds");
    }

    //todo: I might have a problem when exchanging into a currency user still does not owns.
    // I need to catch the case, and probably initialize/save a wc for it before exchanging.

    fromCurrency.setQuantity(fromCurrency.getQuantity() - quantity);
    toCurrency.setQuantity(toCurrency.getQuantity() + (quantity * rate));

    wcRepository.save(fromCurrency);
    wcRepository.save(toCurrency);

    return ExchangeDTO.builder()
        .from(from)
        .to(to)
        .total(quantity * rate)
        .rate(rate)
        .build();
  }

  private void addCurrency(Wallet wallet, String from) {
    var new_currency = WalletCurrency.builder()
        .currency(cRepository.findByShortName(from.toUpperCase()))
        .quantity(0.0)
        .wallet(wallet)
        .build();
    List<WalletCurrency> walletCurrencies = wallet.getWallet_currencies();

    walletCurrencies.add(new_currency);
    wRepository.save(wallet);
  }

  private Double calculateRate(String from, String to) {
    String url = CONVERSION_BASEURL + to.toUpperCase() + "&base_currency=" + from.toUpperCase();

    HttpEntity<String> entity = new HttpEntity<>(null);
    try {
      ResponseEntity<ConversionDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, ConversionDTO.class);
      ConversionDTO data = response.getBody();
      assert data != null;
      return data.getData().get(to.toUpperCase()).getValue();
    } catch (HttpClientErrorException.UnprocessableEntity e) {
      throw new UnprocessableEntityException("Invalid currency");
    }
  }
}
