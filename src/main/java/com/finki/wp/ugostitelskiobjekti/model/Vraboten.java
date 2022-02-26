package com.finki.wp.ugostitelskiobjekti.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
//    @ManyToMany
//    private List<UgostitelskiObjekt> ugostitelskiObjektList;

    public Vraboten(String username, LocalDate datumNaVrabotuvanje) {
        this.username=username;
        this.datumNaVrabotuvanje = datumNaVrabotuvanje;
    }

    public Vraboten() {

    }

//    public Vraboten(String username,Date datumNaVrabotuvanje) {
//        this.username=username;
//        this.datumNaVrabotuvanje = datumNaVrabotuvanje;
//    }
}
