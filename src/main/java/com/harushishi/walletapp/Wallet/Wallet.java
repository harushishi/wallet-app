package com.harushishi.walletapp.Wallet;

import com.harushishi.walletapp.WalletCurrency.WalletCurrency;
import com.harushishi.walletapp.user.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    private Long id;

    @OneToOne(mappedBy = "wallet")
    private User user;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wallet_id")
    private List<WalletCurrency> wallet_currencies;
}
