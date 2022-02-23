package com.finki.wp.ugostitelskiobjekti.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class UgostitelskiObjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imeNaObjekt;
    private Integer slobodniMasi;
    private Integer vkupnoMasi;
    private String adresa;
    private String opis;
    private String urlImg;

    @OneToMany
    private List<Rezervacija> rezervacijaList;

    @ManyToOne
    private Grad grad;

    @ManyToOne
    private Shef shef;

    @OneToMany
    private List<Vraboten> vrabotenList;


    public UgostitelskiObjekt() {
    }

    public UgostitelskiObjekt(String imeNaObjekt, Integer vkupnoMasi, String adresa, String opis, String urlImg, Grad grad, Shef shef, List<Vraboten> vrabotenList) {
        this.imeNaObjekt = imeNaObjekt;
        this.vkupnoMasi = vkupnoMasi;
        this.slobodniMasi = vkupnoMasi;
        this.adresa = adresa;
        this.opis = opis;
        this.urlImg = urlImg;
        this.rezervacijaList = new ArrayList<>();
        this.grad = grad;
        this.shef = shef;
        this.vrabotenList = vrabotenList;
    }
}
