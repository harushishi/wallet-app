package com.harushishi.walletapp.Auth;

import com.harushishi.walletapp.Config.JwtService;
import com.harushishi.walletapp.Currency.CurrencyRepository;
import com.harushishi.walletapp.ErrorHandling.UniqueConstraintException;
import com.harushishi.walletapp.Profile.ProfileRepository;
import com.harushishi.walletapp.Wallet.Wallet;
import com.harushishi.walletapp.Wallet.WalletRepository;
import com.harushishi.walletapp.User.User;
import com.harushishi.walletapp.Profile.Profile;
import com.harushishi.walletapp.User.UserRepository;
import com.harushishi.walletapp.WalletCurrency.WalletCurrency;
import com.harushishi.walletapp.WalletCurrency.WalletCurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final CurrencyRepository currencyRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private WalletCurrencyRepository walletCurrencyRepository;

  public AuthenticationResponse register(RegisterRequest request) {
    try {

      if (userRepository.findByEmail(request.getEmail()).isPresent()) {
        throw new UniqueConstraintException("Mail already in use");
      }


      var user = User.builder()
          .email(request.getEmail())
          .password(passwordEncoder.encode(request.getPassword()))
          .role(Role.USER)
          .build();

      var profile = Profile.builder()
          .user(user)
          .userName(user.getUsername())
          .build();

      var wallet = Wallet.builder()
          .user(user)
          .build();

      var defaultCurrency = WalletCurrency.builder()
          .currency(currencyRepository.findByShortName("ARS"))
          .quantity((float) 0)
          .build();

      var extraCurrency = WalletCurrency.builder()
          .currency(currencyRepository.findByShortName("USD"))
          .quantity((float) 0)
          .build();

      wallet.setWalletCurrency(defaultCurrency);
      wallet.setWalletCurrency(extraCurrency);
      user.setProfile(profile);
      user.setWallet(wallet);
      profile.setUser(user);
      wallet.setUser(user);


      userRepository.save(user);

      var token = jwtService.generateToken(user);

      return AuthenticationResponse.builder()
          .token(token)
          .build();

    } catch (Exception error) {

      throw error;

    }
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );

    var user = userRepository.findByEmail(request.getEmail())
        .orElseThrow();

    var token = jwtService.generateToken(user);

    return AuthenticationResponse.builder()
        .token(token)
        .build();
  }
}

