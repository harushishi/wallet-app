package com.harushishi.walletapp.User;

import com.harushishi.walletapp.Auth.Role;
import com.harushishi.walletapp.WalletCurrency.WalletCurrency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  private Long id;
  private String email;
  private Role role;
  private ProfileDTO profile;
  private Long wallet_id;
  private List<WalletCurrency> wallet_currencies;

  public UserDTO(Long id, String email, Role role, Long pId, String fullName,
                 String userName, Long phoneNumber, Long wId) {
    this.id = id;
    this.email = email;
    this.role = role;
    this.profile = new ProfileDTO(pId, fullName, userName, phoneNumber);
    this.wallet_id = wId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public ProfileDTO getProfile() {
    return profile;
  }

  public void setProfile(ProfileDTO profile) {
    this.profile = profile;
  }

  public Long getWallet_id() {
    return wallet_id;
  }

  public void setWallet_id(Long wallet_id) {
    this.wallet_id = wallet_id;
  }

  public List<WalletCurrency> getWallet_currencies() {
    return wallet_currencies;
  }

  public void setWallet_currencies(List<WalletCurrency> wallet_currencies) {
    this.wallet_currencies = wallet_currencies;
  }

  public static class ProfileDTO {
    private long id;
    private String full_name;

    private String username;

    private Long phone_number;

    public ProfileDTO(Long pId, String full_name, String username, Long phone_number) {
      this.id = pId;
      this.full_name = full_name;
      this.username = username;
      this.phone_number = phone_number;
    }

    public long getId() {
      return id;
    }

    public void setId(long id) {
      this.id = id;
    }

    public String getFull_name() {
      return full_name;
    }

    public void setFull_name(String full_name) {
      this.full_name = full_name;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public Long getPhone_number() {
      return phone_number;
    }

    public void setPhone_number(Long phone_number) {
      this.phone_number = phone_number;
    }
  }

}




