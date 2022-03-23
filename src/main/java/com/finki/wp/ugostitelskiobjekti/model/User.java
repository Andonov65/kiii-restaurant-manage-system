package com.finki.wp.ugostitelskiobjekti.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "catering_users")
public class User implements UserDetails {
    @Id
    private String username;

    private String password;

    private String name;

    private String surname;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    String telefeonskiBroj;

    //    @OneToMany(mappedBy = "user" ,fetch = FetchType.EAGER)
//    private List<ShoppingCart> carts;
//za eden korisnik negovute koshnichki
    //eden user kon povekje ccart objekti
    //mappedBy = deka e obratno od kolanata user
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public User(String username, String password, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public User(String username, String password, String name, String surname, Role role, String telBr) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.telefeonskiBroj = telBr;
    }

    public User() {

    }

    //doznava koj se ulogi gi ima korisnikot
    //nie tie informacii gi chuvame vo role
    //nie sega napravivme many to one
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    //da otkrie dali nekoj acc e suspendiran
    //dali e aktiviran
    //primer koga mora so mail da go potvrdime acc koga se registrirane
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
