package com.harushishi.walletapp.Wallet;

import com.harushishi.walletapp.WalletCurrency.WalletCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

  @Query("select w.wallet_currencies from Wallet w where w.user.id=:userId")
  List<WalletCurrency> getUserCurrenciesById(@Param("userId") Long userId);

  Wallet getWalletById(Long id);
}
