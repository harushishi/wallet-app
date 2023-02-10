package com.harushishi.walletapp.Currency;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
  Currency findByShortName(String shortName);

}
