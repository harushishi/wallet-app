package com.harushishi.walletapp.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  @Query("select new com.harushishi.walletapp.User.UserDTO(u.id, u.email, u.role, p.id, p.fullName," +
      " p.userName, p.phoneNumber, w.id) " +
      "from User u join u.profile as p join u.wallet as w where u.id=:userId")
  UserDTO getUserById(@Param("userId") Long userId);
}

