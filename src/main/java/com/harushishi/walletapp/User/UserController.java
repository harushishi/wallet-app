package com.harushishi.walletapp.User;

import com.harushishi.walletapp.WalletCurrency.ExchangeDTO;
import com.harushishi.walletapp.WalletCurrency.WalletCurrency;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
  private static final String STRING_KEY_PREFIX = "redi2read:strings:";
  private final UserService service;
  @Autowired
  private RedisTemplate<String, String> template;

  @PostMapping("/redis")
  @ResponseStatus(HttpStatus.CREATED)
  public Map.Entry<String, String> setString(@RequestBody Map.Entry<String, String> kvp) {
    template.opsForValue().set(STRING_KEY_PREFIX + kvp.getKey(), kvp.getValue());

    return kvp;
  }

  @PostMapping("/convert")
  @ResponseStatus(HttpStatus.OK)
  public ExchangeDTO convert(@NonNull HttpServletRequest request, @RequestParam String from,
                             @RequestParam String to) {
    var token = request.getHeader("Authorization").substring(7);

    return service.convert(token, from, to);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public UserDTO getUser(@NonNull HttpServletRequest request) {

    var token = request.getHeader("Authorization").substring(7);
    UserDTO user = service.getUser(token);

    System.out.println(user);

    return user;
  }

  @PatchMapping("/deposit")
  @ResponseStatus(HttpStatus.OK)
  public WalletCurrency depositCurrency
      (@NonNull HttpServletRequest request, @RequestParam String currency, @RequestParam Double quantity) {

    var token = request.getHeader("Authorization").substring(7);

    return service.deposit(token, currency, quantity);
  }

  @PostMapping("/exchange")
  @ResponseStatus(HttpStatus.OK)
  public ExchangeDTO exchangeCurrency
      (@NonNull HttpServletRequest request, @RequestParam String from,
       @RequestParam String to, @RequestParam Double quantity) {

    var token = request.getHeader("Authorization").substring(7);

    return service.exchange(token, from, to, quantity);
  }
}
