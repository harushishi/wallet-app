package com.harushishi.walletapp.WalletCurrency;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.harushishi.walletapp.Currency.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@Data
@Builder
@NoArgsConstructor
public class ExchangeDTO {
  private String from;
  private String to;

  private Double total;

  private Double rate;

  public ExchangeDTO(String from, String to, Double total, Double rate) {
    DecimalFormat df = new DecimalFormat("#.###");
    this.from = from;
    this.to = to;
    this.total = Double.parseDouble(df.format(total));
    this.rate = Double.parseDouble(df.format(rate));
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public Double getRate() {
    return rate;
  }

  public void setRate(Double rate) {
    this.rate = rate;
  }
}
