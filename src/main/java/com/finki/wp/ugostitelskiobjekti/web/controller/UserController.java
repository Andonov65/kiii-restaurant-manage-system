package com.finki.wp.ugostitelskiobjekti.web.controller;

import com.finki.wp.ugostitelskiobjekti.Service.UgostitelskiObjektService;
import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {
private final UgostitelskiObjektService ugostitelskiObjektService;

    public UserController(UgostitelskiObjektService ugostitelskiObjektService) {
        this.ugostitelskiObjektService = ugostitelskiObjektService;
    }

    @GetMapping("book/{id}")
    public String getBookinForm(Model model, @PathVariable Long id){
        //vo modelot dodadi go imeto na ugostitelskiot objekt
        UgostitelskiObjekt ugostitelskiObjekt=this.ugostitelskiObjektService.findById(id);
        model.addAttribute("object",ugostitelskiObjekt);
        model.addAttribute("bodyContent","bookingForm");
return "master-template";
    }

    @PostMapping( "/book")
    public String makeReservation(Model model,
                              @RequestParam String objectName,
                              @RequestParam Integer numPersons,
                              @RequestParam Date date,
                              @RequestParam Time time) {
        model.addAttribute("bodyContent","home");

        return "master-template";
    }
}
