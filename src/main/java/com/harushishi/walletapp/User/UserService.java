package com.harushishi.walletapp.User;

import com.harushishi.walletapp.Config.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;
  private final JwtService jwtService;

  public UserDTO getUser(String token) {

    Claims userInfo = jwtService.extractAllClaims(token);

    UserDTO result = repository.getUserById(Long.valueOf(userInfo.getId()));

    System.out.println(result);

    return result;
  }

}
