package com.harushishi.walletapp.Wallet;

import com.harushishi.walletapp.WalletCurrency.WalletCurrency;
import com.harushishi.walletapp.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.collection.spi.PersistentBag;

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
  private List<WalletCurrency> walletCurrencies;

  public Wallet(User user) {

    this.user = user;


    walletCurrencies = new ArrayList<>();

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

  public List<WalletCurrency> getWalletCurrencies() {
    if (walletCurrencies == null) {
      walletCurrencies = new ArrayList<>();
    }
    return walletCurrencies;
  }

  public void setWalletCurrencies(ArrayList<WalletCurrency> walletCurrencies) {
    this.walletCurrencies = walletCurrencies;
  }
}
