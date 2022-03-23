package com.finki.wp.ugostitelskiobjekti.Service;

import com.finki.wp.ugostitelskiobjekti.model.Klient;
import com.finki.wp.ugostitelskiobjekti.model.Rezervacija;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RezervacijaService {
     Rezervacija makeReservation(Long idObject, String klientUserName, Integer numPersons, LocalDate date, LocalTime time);

     List<Rezervacija> showReservations(String username);
     List<Rezervacija> showDoneReservations(Long objectId);

     void acceptReservation(Long id);

     void deleteReservation(Long id);
}
