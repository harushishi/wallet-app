package com.harushishi.walletapp.Wallet;

import com.harushishi.walletapp.WalletCurrency.WalletCurrency;
import com.harushishi.walletapp.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wallets")
public class Wallet {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
  @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<WalletCurrency> wallet_currencies;

  public Wallet(User user) {

    this.user = user;


    wallet_currencies = new ArrayList<>();

  }

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<WalletCurrency> getWallet_currencies() {
    if (wallet_currencies == null) {
      wallet_currencies = new ArrayList<>();
    }
    return wallet_currencies;
  }

  public void setWallet_currencies(ArrayList<WalletCurrency> walletCurrencies) {
    this.wallet_currencies = walletCurrencies;
  }
}
