package com.finki.wp.ugostitelskiobjekti.web.controller;

import com.finki.wp.ugostitelskiobjekti.Service.RezervacijaService;
import com.finki.wp.ugostitelskiobjekti.Service.UgostitelskiObjektService;
import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UgostitelskiObjektService ugostitelskiObjektService;
    private final RezervacijaService rezervacijaService;

    public UserController(UgostitelskiObjektService ugostitelskiObjektService, RezervacijaService rezervacijaService) {
        this.ugostitelskiObjektService = ugostitelskiObjektService;
        this.rezervacijaService = rezervacijaService;
    }

    @GetMapping("/book/{id}")
    public String getBookinForm(Model model, @PathVariable Long id) {
        //vo modelot dodadi go imeto na ugostitelskiot objekt
        UgostitelskiObjekt ugostitelskiObjekt = this.ugostitelskiObjektService.findById(id);
        model.addAttribute("object", ugostitelskiObjekt);
        model.addAttribute("bodyContent", "bookingForm");
        return "master-template";
    }

    @PostMapping("/book")
    public String makeReservation(Model model,
                                  @RequestParam Long objectId,
                                  @RequestParam String klientUserName,
                                  @RequestParam Integer numPersons,
                                  @RequestParam String date,
                                  @RequestParam String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalTime localTime = LocalTime.parse(time);
        //now save it to RReservations

        this.rezervacijaService.makeReservation(objectId, klientUserName, numPersons, localDate, localTime);


        return "redirect:/home";
    }

    @GetMapping("/reservations")
    public String showReservations(Model model, @RequestParam String username){
        model.addAttribute("rezervacii", this.rezervacijaService.showReservations(username));
        model.addAttribute("bodyContent", "reservationsList");

        return "master-template";
    }

    @GetMapping("/reservationsEmployee")
    public String showReservationsVraboten(Model model, @RequestParam String username){
        model.addAttribute("rezervacii", this.rezervacijaService.showReservations(username));
        model.addAttribute("bodyContent", "reservationsList");

        return "master-template";
    }


}
