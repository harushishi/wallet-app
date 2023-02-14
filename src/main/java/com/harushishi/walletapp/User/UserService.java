package com.harushishi.walletapp.User;

import com.harushishi.walletapp.Config.JwtService;
import com.harushishi.walletapp.Wallet.WalletRepository;
import com.harushishi.walletapp.WalletCurrency.WalletCurrency;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  private final WalletRepository wRepository;
  private final JwtService jwtService;

  public UserDTO getUser(String token) {

    Claims userInfo = jwtService.extractAllClaims(token);

    UserDTO user = repository.getUserById(Long.valueOf(userInfo.getId()));

    List<WalletCurrency> currencies = wRepository.getUserCurrenciesById(Long.valueOf(userInfo.getId()));

    user.setWalletCurrencies(currencies);

    return user;
  }

}
