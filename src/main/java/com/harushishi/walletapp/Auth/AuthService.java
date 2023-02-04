package com.harushishi.walletapp.Auth;

import com.harushishi.walletapp.Config.JwtService;
import com.harushishi.walletapp.ErrorHandling.UniqueConstraintException;
import com.harushishi.walletapp.user.User;
import com.harushishi.walletapp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    try {

      if (repository.findByEmail(request.getEmail()).isPresent()) {
        throw new UniqueConstraintException("Mail already in use");
      }

      var user = User.builder()
          .email(request.getEmail())
          .password(passwordEncoder.encode(request.getPassword()))
          .role(Role.USER)
          .build();

      repository.save(user);

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

    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();

    var token = jwtService.generateToken(user);

    return AuthenticationResponse.builder()
        .token(token)
        .build();
  }
}


/*@Service
public class AuthService {
    private final UserRepository repository;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    public User register(RegisterDTO payload) {
        try {
            return repository.save(new User(payload.getEmail(), payload.getPassword()));
        } catch (Exception error) {
            throw error;
        }
    }
}*/
