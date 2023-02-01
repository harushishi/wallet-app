package com.harushishi.walletapp.Auth;
import com.harushishi.walletapp.Auth.dto.RegisterDTO;
import com.harushishi.walletapp.ErrorHandling.UniqueConstraintException;
import com.harushishi.walletapp.user.User;
import com.harushishi.walletapp.user.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.crypto.Data;

@RestController
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
        }catch(Exception error) {
            if(error instanceof DataIntegrityViolationException) {
                throw new UniqueConstraintException("Email already in use");
            } else {
                throw new ResponseStatusException
                        (HttpStatus.INTERNAL_SERVER_ERROR, "Internal error", error);
            }
        }
    }
}
