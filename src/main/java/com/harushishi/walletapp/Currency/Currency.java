package com.harushishi.walletapp.Currency;

import com.harushishi.walletapp.WalletCurrency.WalletCurrency;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "currencies")
public class Currency {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalletCurrency> walletCurrencies;
}
