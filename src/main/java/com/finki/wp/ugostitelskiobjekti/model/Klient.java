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

//    @OneToMany
//    private List<Rezervacija> rezervacijaList;

    public Klient() {
    }

    public Klient(Integer vozrast) {
        this.vozrast = vozrast;
    }
}
