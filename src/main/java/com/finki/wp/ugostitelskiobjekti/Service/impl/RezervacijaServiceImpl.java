package com.finki.wp.ugostitelskiobjekti.Service.impl;

import com.finki.wp.ugostitelskiobjekti.Service.RezervacijaService;
import com.finki.wp.ugostitelskiobjekti.Service.UgostitelskiObjektService;
import com.finki.wp.ugostitelskiobjekti.model.Klient;
import com.finki.wp.ugostitelskiobjekti.model.Rezervacija;
import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import com.finki.wp.ugostitelskiobjekti.repositories.KlientRepositoryJPA;
import com.finki.wp.ugostitelskiobjekti.repositories.RezervacijaRepositoryJPA;
import com.finki.wp.ugostitelskiobjekti.repositories.UgostitelskiObjektRepositoryJPA;
import com.finki.wp.ugostitelskiobjekti.repositories.VrabotenRepositoryJPA;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RezervacijaServiceImpl implements RezervacijaService {
    private final UgostitelskiObjektService ugostitelskiObjektService;
    private final UgostitelskiObjektRepositoryJPA ugostitelskiObjektRepositoryJPA;
    private final KlientRepositoryJPA klientRepositoryJPA;
    private final RezervacijaRepositoryJPA rezervacijaRepositoryJPA;
    private final VrabotenRepositoryJPA vrabotenRepositoryJPA;

    public RezervacijaServiceImpl(UgostitelskiObjektService ugostitelskiObjektService, UgostitelskiObjektRepositoryJPA ugostitelskiObjektRepositoryJPA, KlientRepositoryJPA klientRepositoryJPA, RezervacijaRepositoryJPA rezervacijaRepositoryJPA, VrabotenRepositoryJPA vrabotenRepositoryJPA) {
        this.ugostitelskiObjektService = ugostitelskiObjektService;
        this.ugostitelskiObjektRepositoryJPA = ugostitelskiObjektRepositoryJPA;
        this.klientRepositoryJPA = klientRepositoryJPA;
        this.rezervacijaRepositoryJPA = rezervacijaRepositoryJPA;
        this.vrabotenRepositoryJPA = vrabotenRepositoryJPA;
    }


    @Override
    public Rezervacija makeReservation(Long idObject, String klientUserName, Integer numPersons, LocalDate date, LocalTime time) {
        //find the object
        UgostitelskiObjekt ugostitelskiObjekt = this.ugostitelskiObjektService.findById(idObject);
        //find klient
        Klient klient = this.klientRepositoryJPA.findByUsername(klientUserName);
        //save changes +set status
        Rezervacija rezervacija = new Rezervacija(numPersons, date, time, klient, ugostitelskiObjekt, "zakazhana");
        this.ugostitelskiObjektRepositoryJPA.findUgostitelskiObjektByImeNaObjekt(ugostitelskiObjekt.getImeNaObjekt()).getRezervacijaList().add(rezervacija);

        return this.rezervacijaRepositoryJPA.save(rezervacija);

    }


    @Transactional
    @Override
    public List<Rezervacija> showReservations(String username) {
        if(this.vrabotenRepositoryJPA.findByUsername(username) != null){
             Optional<UgostitelskiObjekt> ugostitelskiObjekt = this.ugostitelskiObjektRepositoryJPA.findAll().stream()
                    .filter(i -> i.getVrabotenList().stream().anyMatch(k -> k.getUsername().equals(username)))
                    .findFirst();

             return ugostitelskiObjekt.get().getRezervacijaList().stream()
                     .filter(o -> o.getStatusRezervacija().equals("zakazhana"))
                     .collect(Collectors.toList());

        }
        return this.rezervacijaRepositoryJPA.findAll().stream()
                .filter(i -> i.getKlient().getUsername().equals(username))
                .filter(o -> o.getStatusRezervacija().equals("zakazhana"))
                .collect(Collectors.toList());
    }



    @Override
    public List<Rezervacija> showDoneReservations(Long objectId) {

            Optional<UgostitelskiObjekt> ugostitelskiObjekt = this.ugostitelskiObjektRepositoryJPA.findById(objectId);

            return ugostitelskiObjekt.get().getRezervacijaList().stream()
                    .filter(o -> o.getStatusRezervacija().equals("realizirana"))
                    .collect(Collectors.toList());


    }


    @Override
    public void acceptReservation(Long id) {
        Rezervacija rezervacija = this.rezervacijaRepositoryJPA.findById(id).get();

                rezervacija.setStatusRezervacija("realizirana");
        this.rezervacijaRepositoryJPA.save(rezervacija);
    }



    @Override
    public void deleteReservation(Long id) {

        this.ugostitelskiObjektRepositoryJPA.findAll()
                .stream()
                .anyMatch(i -> i.getRezervacijaList().removeIf(o -> o.getId().equals(id)));

        this.rezervacijaRepositoryJPA.deleteById(id);
    }


}
