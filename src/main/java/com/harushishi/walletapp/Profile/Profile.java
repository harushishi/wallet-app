package com.harushishi.walletapp.Profile;

import com.harushishi.walletapp.Location.Address;
import com.harushishi.walletapp.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {

  @Id
  @GeneratedValue
  private Long id;
  @Column(name = "full_name")
  private String full_name;
  @Column(name = "username", nullable = false, unique = true)
  private String username;
  @Column(name = "phone_number", unique = true)
  private Long phone_number;
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
  @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
  private Address address;

  public Long getId() {
    return id;
  }

  public String getFull_name() {
    return full_name;
  }

  public String getUsername() {
    return username;
  }

  public Long getPhone_number() {
    return phone_number;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Address getAddress() {
    return address;
  }
}
