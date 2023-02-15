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
  private Double quantity;

  @ManyToOne
  @JoinColumn(name = "currency_id")
  private Currency currency;

  @ManyToOne
  @JoinColumn(name = "wallet_id")
  private Wallet wallet;

  public Long getId() {
    return id;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  @Override
  public String toString() {
    return "WalletCurrency{" +
        "id=" + id +
        ", quantity=" + quantity +
        ", currency=" + currency +
        ", wallet=" + wallet +
        '}';
  }
}
