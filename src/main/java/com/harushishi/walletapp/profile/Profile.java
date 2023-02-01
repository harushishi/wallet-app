package com.harushishi.walletapp.profile;

import com.harushishi.walletapp.Location.Address;
import com.harushishi.walletapp.user.User;
import jakarta.persistence.*;

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
}
