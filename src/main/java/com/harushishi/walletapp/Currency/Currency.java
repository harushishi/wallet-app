package com.harushishi.walletapp.Currency;

import com.harushishi.walletapp.WalletCurrency.WalletCurrency;
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
@Table(name = "currencies")
public class Currency {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "shortName")
  private String shortName;
  @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<WalletCurrency> walletCurrencies;

  public void setName(String name) {
    this.name = name;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }
}
