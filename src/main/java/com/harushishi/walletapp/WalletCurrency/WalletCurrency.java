package com.harushishi.walletapp.WalletCurrency;

import com.harushishi.walletapp.Currency.Currency;
import jakarta.persistence.*;

@Entity
@Table(name = "wallet_currencies")
public class WalletCurrency {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "quantity")
    private Float quantity;
    @Column (name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "currency_id")
    private Currency currency;
}
