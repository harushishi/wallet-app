package com.harushishi.walletapp.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  //No me esta andando si agrego wallet y profile. probablemente por
  //que actualmente son null. tengo que probar creandolos previamente.
  @Query("select new com.harushishi.walletapp.User.UserDTO" +
      " (u.email, u.role)" +
      " from User u where u.id = 1")
  UserDTO getUserById(Long id);
}

