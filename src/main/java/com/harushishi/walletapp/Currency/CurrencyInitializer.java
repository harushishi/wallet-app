package com.harushishi.walletapp.Currency;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyInitializer {
  private final CurrencyRepository currencyRepository;

  @PostConstruct
  public void init() {
    if (currencyRepository.findAll().isEmpty()) {
      Currency ars = new Currency();
      ars.setName("Argentine Peso");
      ars.setShortName("ARS");
      currencyRepository.save(ars);

      Currency usd = new Currency();
      usd.setName("United States Dollar");
      usd.setShortName("USD");
      currencyRepository.save(usd);

      Currency eur = new Currency();
      eur.setName("Euro");
      eur.setShortName("EUR");
      currencyRepository.save(eur);

      Currency btc = new Currency();
      btc.setName("Bitcoin");
      btc.setShortName("BTC");
      currencyRepository.save(btc);

      Currency eth = new Currency();
      btc.setName("Ethereum");
      btc.setShortName("ETH");
      currencyRepository.save(btc);

      Currency bnb = new Currency();
      btc.setName("Binance Coin");
      btc.setShortName("BNB");
      currencyRepository.save(bnb);
    }
  }
}
