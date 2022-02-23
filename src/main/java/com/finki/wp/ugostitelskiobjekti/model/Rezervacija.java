package com.finki.wp.ugostitelskiobjekti.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer vkupnoLugje;

    @ManyToOne
    private Klient klient;

    @ManyToOne
    private UgostitelskiObjekt ugostitelskiObjekt;


    public Rezervacija() {
    }

    public Rezervacija(Integer vkupnoLugje, Klient klient, UgostitelskiObjekt ugostitelskiObjekt) {
        this.vkupnoLugje = vkupnoLugje;
        this.klient = klient;
        this.ugostitelskiObjekt = ugostitelskiObjekt;
    }
}
