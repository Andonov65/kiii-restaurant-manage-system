package com.finki.wp.ugostitelskiobjekti.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Chovek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String ime;
    private String prezime;
    private String telefonski_broj;

    @Enumerated(value = EnumType.STRING)
    private Role role;


    public Chovek() {
    }

    public Chovek(String username, String password, String ime, String prezime, String telefonski_broj, Role role) {
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.telefonski_broj = telefonski_broj;
        this.role = role;
    }
}
