package com.finki.wp.ugostitelskiobjekti.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer vkupnoLugje;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    private LocalTime localTime;

    @ManyToOne
    private Klient klient;

    @ManyToOne
    private UgostitelskiObjekt ugostitelskiObjekt;

String statusRezervacija;
    public Rezervacija() {
    }

    public Rezervacija(Integer vkupnoLugje, Klient klient, UgostitelskiObjekt ugostitelskiObjekt) {
        this.vkupnoLugje = vkupnoLugje;
        this.klient = klient;
        this.ugostitelskiObjekt = ugostitelskiObjekt;
    }
}
