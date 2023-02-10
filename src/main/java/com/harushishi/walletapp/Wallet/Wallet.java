package com.harushishi.walletapp.Wallet;

import com.harushishi.walletapp.WalletCurrency.WalletCurrency;
import com.harushishi.walletapp.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "wallets")
public class Wallet {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne(mappedBy = "wallet")
  private User user;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "wallet_id")
  private List<WalletCurrency> wallet_currencies;

  public Wallet() {
    this.wallet_currencies = new ArrayList<>();
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setWalletCurrency(WalletCurrency walletCurrency) {
    if (this.wallet_currencies == null) {
      this.wallet_currencies = new ArrayList<>();
    }
    this.wallet_currencies.add(walletCurrency);
  }
}
