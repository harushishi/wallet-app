package com.harushishi.walletapp.WalletCurrency;

import com.harushishi.walletapp.Wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletCurrencyRepository extends JpaRepository<WalletCurrency, Long> {
  WalletCurrency findByWalletAndCurrency_ShortName(Wallet wallet, String currency_shortName);
}
