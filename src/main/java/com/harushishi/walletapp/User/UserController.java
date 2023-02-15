package com.harushishi.walletapp.User;

import com.harushishi.walletapp.WalletCurrency.ExchangeDTO;
import com.harushishi.walletapp.WalletCurrency.WalletCurrency;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService service;

  @GetMapping
  public UserDTO getUser(@NonNull HttpServletRequest request) {

    var token = request.getHeader("Authorization").substring(7);
    UserDTO user = service.getUser(token);

    System.out.println(user);

    return user;
  }

  @PatchMapping("/deposit")
  public WalletCurrency depositCurrency
      (@NonNull HttpServletRequest request, @RequestParam String currency, @RequestParam Double quantity) {

    var token = request.getHeader("Authorization").substring(7);

    return service.deposit(token, currency, quantity);
  }

  @PostMapping("/exchange")
  public ExchangeDTO exchangeCurrency
      (@NonNull HttpServletRequest request, @RequestParam String from,
       @RequestParam String to, @RequestParam Double quantity) {

    var token = request.getHeader("Authorization").substring(7);

    return service.exchange(token, from, to, quantity);
  }
}
