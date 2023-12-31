package com.home.login.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


@Table(name = "users")
@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable=false, unique=true)
    private String username;
    @Column(nullable=false)
    private String password;
    private String name;
    private String gender;
    private String email;
    @OneToMany
    private List<Document> documents;
    @OneToMany
    private List<Address> addresses;
    @OneToMany
    private List<Phone> phones;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Profiles> authorities;

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
