package com.harushishi.walletapp.User;

import com.harushishi.walletapp.Auth.Role;
import com.harushishi.walletapp.Wallet.Wallet;
import com.harushishi.walletapp.Profile.Profile;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

  @Id
  @GeneratedValue
  private long id;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Profile profile;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Wallet wallet;
  @Enumerated(EnumType.STRING)
  private Role role;

  public User(String email, String password, Role role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public long getId() {
    return id;
  }

  public Role getRole() {
    return role;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
    profile.setUser(this);
  }

  public void setWallet(Wallet wallet) {
    this.wallet = wallet;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
