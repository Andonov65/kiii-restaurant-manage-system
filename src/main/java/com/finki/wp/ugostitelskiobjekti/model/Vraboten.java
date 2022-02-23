package com.finki.wp.ugostitelskiobjekti.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Vraboten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date datumNaVrabotuvanje;

//    @ManyToMany
//    private List<UgostitelskiObjekt> ugostitelskiObjektList;

    public Vraboten() {
    }

    public Vraboten(Date datumNaVrabotuvanje) {
        this.datumNaVrabotuvanje = datumNaVrabotuvanje;
    }
}
