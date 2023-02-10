package com.harushishi.walletapp.User;

import com.harushishi.walletapp.Auth.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  private String email;
  private Role role;

}
