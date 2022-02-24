package com.finki.wp.ugostitelskiobjekti.web.controller;

import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import com.finki.wp.ugostitelskiobjekti.service.UgostitelskiObjektService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"/home", "/"})
public class HomeController {

    private final UgostitelskiObjektService ugostitelskiObjektService;

    public HomeController(UgostitelskiObjektService ugostitelskiObjektService) {
        this.ugostitelskiObjektService = ugostitelskiObjektService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        List<UgostitelskiObjekt> ugostitelskiObjektList = this.ugostitelskiObjektService.findAll();
       model.addAttribute("objekti", ugostitelskiObjektList);
       model.addAttribute("bodyContent", "home");
       return "master-template";
    }

    @GetMapping("/details/{id}")
    public String getViewObject(@PathVariable Long id, Model model) {
        UgostitelskiObjekt ugostitelskiObjekt = this.ugostitelskiObjektService.findById(id);
        model.addAttribute("objekt", ugostitelskiObjekt);
        model.addAttribute("bodyContent", "viewObject");
        return "master-template";
    }




}
