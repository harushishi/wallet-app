package com.harushishi.walletapp.User;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
