package com.harushishi.walletapp.WalletCurrency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversionDTO {
  private Meta meta;
  private Map<String, CurrencyData> data;

  public Meta getMeta() {
    return meta;
  }

  public void setMeta(Meta meta) {
    this.meta = meta;
  }

  public Map<String, CurrencyData> getData() {
    return data;
  }

  public void setData(Map<String, CurrencyData> data) {
    this.data = data;
  }

  public static class Meta {
    @JsonProperty("last_updated_at")
    private Instant lastUpdatedAt;

    public Instant getLastUpdatedAt() {
      return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Instant lastUpdatedAt) {
      this.lastUpdatedAt = lastUpdatedAt;
    }
  }

  public static class CurrencyData {
    private String code;
    private double value;

    public String getCode() {
      return code;
    }

    public void setCode(String code) {
      this.code = code;
    }

    public double getValue() {
      return value;
    }

    public void setValue(double value) {
      this.value = value;
    }
  }
}




