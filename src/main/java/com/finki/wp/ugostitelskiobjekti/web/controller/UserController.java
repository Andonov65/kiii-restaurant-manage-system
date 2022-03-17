package com.finki.wp.ugostitelskiobjekti.web.controller;

import com.finki.wp.ugostitelskiobjekti.Service.UgostitelskiObjektService;
import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UgostitelskiObjektService ugostitelskiObjektService;

    public UserController(UgostitelskiObjektService ugostitelskiObjektService) {
        this.ugostitelskiObjektService = ugostitelskiObjektService;
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
                                  @RequestParam Integer numPersons,
                                  @RequestParam String date,
                                  @RequestParam String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalTime localTime = LocalTime.parse(time);



        return "redirect:/home";
    }
}
