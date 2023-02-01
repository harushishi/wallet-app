package com.harushishi.walletapp.Auth;

import com.harushishi.walletapp.Auth.dto.RegisterDTO;
import com.harushishi.walletapp.user.User;
import com.harushishi.walletapp.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repository;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    public User register(RegisterDTO payload){
        try {
           return repository.save(new User(payload.getEmail(), payload.getPassword()));
        } catch(Exception error) {
            throw error;
        }
    }
}
