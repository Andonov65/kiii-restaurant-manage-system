package com.finki.wp.ugostitelskiobjekti.Service;

import com.finki.wp.ugostitelskiobjekti.model.Klient;
import com.finki.wp.ugostitelskiobjekti.model.Rezervacija;

import java.time.LocalDate;
import java.time.LocalTime;

public interface RezervacijaService {
    public Rezervacija makeReservation(Long idObject, String klientUserName, Integer numPersons, LocalDate date, LocalTime time);
}
