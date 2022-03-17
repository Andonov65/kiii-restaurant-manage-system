package com.finki.wp.ugostitelskiobjekti.Service.impl;

import com.finki.wp.ugostitelskiobjekti.Service.RezervacijaService;
import com.finki.wp.ugostitelskiobjekti.Service.UgostitelskiObjektService;
import com.finki.wp.ugostitelskiobjekti.model.Klient;
import com.finki.wp.ugostitelskiobjekti.model.Rezervacija;
import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import com.finki.wp.ugostitelskiobjekti.model.Vraboten;
import com.finki.wp.ugostitelskiobjekti.repositories.KlientRepositoryJPA;
import com.finki.wp.ugostitelskiobjekti.repositories.RezervacijaRepositoryJPA;
import com.finki.wp.ugostitelskiobjekti.repositories.UgostitelskiObjektRepositoryJPA;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RezervacijaServiceImpl implements RezervacijaService {
    private final UgostitelskiObjektService ugostitelskiObjektService;
    private final KlientRepositoryJPA klientRepositoryJPA;
    private final RezervacijaRepositoryJPA rezervacijaRepositoryJPA;

    public RezervacijaServiceImpl(UgostitelskiObjektService ugostitelskiObjektService, KlientRepositoryJPA klientRepositoryJPA, RezervacijaRepositoryJPA rezervacijaRepositoryJPA) {
        this.ugostitelskiObjektService = ugostitelskiObjektService;
        this.klientRepositoryJPA = klientRepositoryJPA;
        this.rezervacijaRepositoryJPA = rezervacijaRepositoryJPA;
    }

    @Override
    public Rezervacija makeReservation(Long idObject, String klientUserName, Integer numPersons, LocalDate date, LocalTime time) {
        //find the object
        UgostitelskiObjekt ugostitelskiObjekt = this.ugostitelskiObjektService.findById(idObject);
        //find klient
        Klient klient = this.klientRepositoryJPA.findByUsername(klientUserName);
        //save changes +set status
        Rezervacija rezervacija = new Rezervacija(numPersons, date, time, klient, ugostitelskiObjekt, "zakazhana");
        return this.rezervacijaRepositoryJPA.save(rezervacija);

    }

    @Override
    public List<Rezervacija> showReservations(String username) {
        return this.rezervacijaRepositoryJPA.findAll().stream()
                .filter(i -> i.getKlient().getUsername().equals(username))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rezervacija> showReservationsEmp(String username) {
        // treba da se zeme eden od vrabotenite vo objektot vo koj raboti i da se prikazhat site rezervacii vo
        // toj objekt
        return null;

    }
}
