package com.harushishi.walletapp.Auth;

import com.harushishi.walletapp.ErrorHandling.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    var result = service.register(request);
    return ResponseEntity.ok(result);
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody AuthenticationRequest request
  ) {
    try {

      return ResponseEntity.ok(service.authenticate(request));

    } catch (Exception e) {

      if (e.getClass().equals(BadCredentialsException.class)) {
        throw new BadRequestException("Wrong credentials");
      }

      throw e;
    }
  }
}




/*@RestController
public class AuthController {
  private final AuthService service;

  public AuthController(UserRepository repository, AuthService service) {
    this.service = service;
  }

  @PostMapping("/register")
  User register(@RequestBody RegisterDTO payload) {
    try {
      User savedUser = service.register(payload);
      System.out.println(savedUser);
      return savedUser;
    } catch (Exception error) {
      if (error instanceof DataIntegrityViolationException) {
        throw new UniqueConstraintException("Email already in use");
      } else {
        throw new ResponseStatusException
            (HttpStatus.INTERNAL_SERVER_ERROR, "Internal error", error);
      }
    }
  }
}*/
