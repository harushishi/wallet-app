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
  private List<WalletCurrency> walletCurrencies;

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

  public List<WalletCurrency> getWalletCurrencies() {
    return walletCurrencies;
  }

  public void setWalletCurrencies(List<WalletCurrency> walletCurrencies) {
    this.walletCurrencies = walletCurrencies;
  }

  public static class ProfileDTO {
    private long id;
    private String fullName;

    private String userName;

    private Long phoneNumber;

    public ProfileDTO(Long pId, String fullName, String userName, Long phoneNumber) {
      this.id = pId;
      this.fullName = fullName;
      this.userName = userName;
      this.phoneNumber = phoneNumber;
    }

    public long getId() {
      return id;
    }

    public void setId(long id) {
      this.id = id;
    }

    public String getFullName() {
      return fullName;
    }

    public void setFullName(String fullName) {
      this.fullName = fullName;
    }

    public String getUserName() {
      return userName;
    }

    public void setUserName(String userName) {
      this.userName = userName;
    }

    public Long getPhoneNumber() {
      return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
      this.phoneNumber = phoneNumber;
    }
  }

}




