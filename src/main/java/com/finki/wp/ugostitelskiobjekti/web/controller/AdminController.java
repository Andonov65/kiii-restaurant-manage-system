package com.finki.wp.ugostitelskiobjekti.web.controller;

import com.finki.wp.ugostitelskiobjekti.model.Grad;
import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import com.finki.wp.ugostitelskiobjekti.model.User;
import com.finki.wp.ugostitelskiobjekti.service.GradService;
import com.finki.wp.ugostitelskiobjekti.service.UgostitelskiObjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
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
                              @RequestParam String ime,
                              @RequestParam String adresa,
                              @RequestParam String opis,
                              @RequestParam MultipartFile slika,
                              @RequestParam Integer vkupnoMasi,
                              @RequestParam String grad,
                              @RequestParam String shef) {
        //  String fileName = StringUtils.cleanPath(slika.getOriginalFilename());//imeto na slikata go zima

        ugostitelskiObjektService.saveObj(ime, adresa, opis, slika, vkupnoMasi, grad, shef);

        return "redirect:/home";
    }

    //dodavanje na vraboten vo baza
    //sopstvenikot za svoite vraboteni gi smisluva lozinkite i korisnichkite iminja
    //kelnerPrvaSmena -->pass
    ///admin/addEmployee
    @GetMapping("/addEmployee")
    public String getAddEmployeeForm(Model model) {
        model.addAttribute("bodyContent", "register");

        return "master-template";//see dodava preku registracija posle
    }
    @GetMapping("/employees")
    public String getEmployeeList(Model model){
        model.addAttribute("bodyContent", "register");

        return "master-template";//see dodava preku registracija posle
    }
}
