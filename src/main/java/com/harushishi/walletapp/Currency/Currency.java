package com.harushishi.walletapp.Currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


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
  @JsonProperty("short_name")
  private String shortName;

  private String currency;

  public Currency(String name, String symbol) {
    this.name = name;
    this.shortName = symbol;
    this.currency = shortName;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }
}
