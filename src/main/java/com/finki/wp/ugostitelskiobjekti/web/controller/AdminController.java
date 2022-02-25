package com.finki.wp.ugostitelskiobjekti.web.controller;

import com.finki.wp.ugostitelskiobjekti.model.Grad;
import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import com.finki.wp.ugostitelskiobjekti.service.GradService;
import com.finki.wp.ugostitelskiobjekti.service.UgostitelskiObjektService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UgostitelskiObjektService ugostitelskiObjektService;
    private final GradService gradService;

    public AdminController(UgostitelskiObjektService ugostitelskiObjektService, GradService gradService) {
        this.ugostitelskiObjektService = ugostitelskiObjektService;
        this.gradService = gradService;
    }


    @GetMapping("/add")
    public String getAddObject(Model model) {

        model.addAttribute("gradovi", this.gradService.findAll());
        model.addAttribute("bodyContent", "addObject");
        return "master-template";
    }

    @GetMapping("/edit/{id}")
    public String getEditObject(Model model, @PathVariable Long id) {
        UgostitelskiObjekt ugostitelskiObjekt = this.ugostitelskiObjektService.findById(id);
        model.addAttribute("objekt", ugostitelskiObjekt);
        model.addAttribute("gradovi", this.gradService.findAll());
        model.addAttribute("bodyContent", "addObject");
        return "master-template";
    }


    //ime=&adresa=&opis=&slika=&vkupnoMasi=&grad=&shef=markoadmin
    @PostMapping("/add")
    public String saveProduct(@RequestParam(required = false) Long id,
                              @RequestParam String ime ,
                              @RequestParam String adresa,
                              @RequestParam String opis,
                              @RequestParam  byte[] slika,
                              @RequestParam Integer vkupnoMasi,
                              @RequestParam String grad,
                              @RequestParam String shef){

        this.ugostitelskiObjektService.save(ime, adresa, opis, slika, vkupnoMasi, grad, shef);
        return "redirect:/home";
    }




}
