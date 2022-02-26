package com.finki.wp.ugostitelskiobjekti.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Klient {
    @Id
    private String username;

    private Integer vozrast;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
//    @OneToMany
//    private List<Rezervacija> rezervacijaList;

    public Klient() {
    }
    public Klient(String username) {
        this.username=username;
    }
    public Klient(Integer vozrast) {
        this.vozrast = vozrast;
    }
}
