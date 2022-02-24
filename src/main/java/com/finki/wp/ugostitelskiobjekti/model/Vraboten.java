package com.finki.wp.ugostitelskiobjekti.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Vraboten {

    @Id
    private String username;

    private Date datumNaVrabotuvanje;

    @OneToOne
    @MapsId
    @JoinColumn(name = "username")
    private User user;
//    @ManyToMany
//    private List<UgostitelskiObjekt> ugostitelskiObjektList;

    public Vraboten() {
    }

    public Vraboten(Date datumNaVrabotuvanje) {
        this.datumNaVrabotuvanje = datumNaVrabotuvanje;
    }
}
