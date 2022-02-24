package com.finki.wp.ugostitelskiobjekti.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {



    @GetMapping("/add")
    public String getAddObject(Model model) {
        model.addAttribute("bodyContent", "addObject");
        return "master-template";
    }

    @GetMapping("/edit/{id}")
    public String getEditObject(Model model, @PathVariable Long id) {
        model.addAttribute("bodyContent", "editObject");
        return "master-template";
    }

    //ime= &adresa= &opis= &slika= &vkupnoMasi=
    @PostMapping("/add")
    public String saveProduct(@RequestParam(required = false) Long id,
                              @RequestParam String ime ,
                              @RequestParam String adresa,
                              @RequestParam String opis,
                              @RequestParam String slika,
                              @RequestParam Integer vkupnoMasi){


        return "redirect:/master-template";
    }



}
