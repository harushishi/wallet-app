package com.harushishi.walletapp.WalletCurrency;

import com.harushishi.walletapp.Currency.Currency;
import com.harushishi.walletapp.Wallet.Wallet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wallet_currencies")
public class WalletCurrency {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "quantity")
  private Float quantity;

  @ManyToOne
  private Wallet wallet;

  @ManyToOne()
  @JoinColumn(name = "currency_id")
  private Currency currency;

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }
}
