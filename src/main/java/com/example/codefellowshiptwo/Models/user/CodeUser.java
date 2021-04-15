package com.example.codefellowshiptwo.Models.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class CodeUser implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long id;

    //    Spring Security is going to require us to create the method on our repository: findByUsername
//    @UniqueConstraint("username")
    @Column(unique=true)
    String username;
    String password;

    @Lob // jpa make this big (large object)
    public String bio;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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