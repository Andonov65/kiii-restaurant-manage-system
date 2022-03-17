package com.finki.wp.ugostitelskiobjekti.Service.impl;

import com.finki.wp.ugostitelskiobjekti.Service.RezervacijaService;
import com.finki.wp.ugostitelskiobjekti.Service.UgostitelskiObjektService;
import com.finki.wp.ugostitelskiobjekti.model.Klient;
import com.finki.wp.ugostitelskiobjekti.model.Rezervacija;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class RezervacijaServiceImpl implements RezervacijaService {
    private final UgostitelskiObjektService ugostitelskiObjektService;

    public RezervacijaServiceImpl(UgostitelskiObjektService ugostitelskiObjektService) {
        this.ugostitelskiObjektService = ugostitelskiObjektService;
    }

    @Override
    public Rezervacija makeReservation(Long idObject, Klient klient, Integer numPersons, LocalDate date, LocalTime time) {
        return null;
    }
}
