package com.finki.wp.ugostitelskiobjekti.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Vraboten {

    @Id
    private String username;

    private LocalDate datumNaVrabotuvanje;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @OneToOne
    @JoinColumn(name = "ime_na_objekt", referencedColumnName = "imeNaObjekt")
    private UgostitelskiObjekt ugostitelskiObjekt;


    public Vraboten() {

    }


}
