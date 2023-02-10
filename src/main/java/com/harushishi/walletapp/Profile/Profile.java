package com.harushishi.walletapp.Profile;

import com.harushishi.walletapp.Location.Address;
import com.harushishi.walletapp.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
  private String fullName;
  @Column(name = "username", nullable = false, unique = true)
  private String userName;
  @Column(name = "phone_number", unique = true)
  private Long phoneNumber;
  @OneToOne(mappedBy = "profile")
  private User user;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  public Profile(String userName, User user) {
    this.userName = userName;
    this.user = user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
