package com.finki.wp.ugostitelskiobjekti.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class UgostitelskiObjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public UgostitelskiObjekt(Integer vkupnoMasi, String adresa, String opis, String urlImg, List<Rezervacija> rezervacijaList, Grad grad, Shef shef, List<Vraboten> vrabotenList) {
        this.vkupnoMasi = vkupnoMasi;
        this.slobodniMasi = vkupnoMasi;
        this.adresa = adresa;
        this.opis = opis;
        this.urlImg = urlImg;
        this.rezervacijaList = rezervacijaList;
        this.grad = grad;
        this.shef = shef;
        this.vrabotenList = vrabotenList;
    }
}
