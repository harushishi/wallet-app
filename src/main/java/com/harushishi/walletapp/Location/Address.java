package com.harushishi.walletapp.Location;

import com.harushishi.walletapp.Profile.Profile;
import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {


  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "country", nullable = false)
  private String country;

  @Column(name = "city", nullable = false)
  private String city;

  @Column(name = "zip_code", nullable = false)
  private Long zip_code;

  @Column(name = "street", nullable = false)
  private String street;

  @Column(name = "number", nullable = false)
  private String number;

  @OneToOne
  @JoinColumn(name = "profile_id")
  private Profile profile;

  public Long getId() {
    return id;
  }

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  public Long getZip_code() {
    return zip_code;
  }

  public String getStreet() {
    return street;
  }

  public String getNumber() {
    return number;
  }

  public Profile getProfile() {
    return profile;
  }
}
