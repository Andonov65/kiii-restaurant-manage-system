package com.finki.wp.ugostitelskiobjekti.web.controller;

import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import com.finki.wp.ugostitelskiobjekti.service.UgostitelskiObjektService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
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
    public String getViewObject(@PathVariable Long id, Model model) throws UnsupportedEncodingException {
        UgostitelskiObjekt ugostitelskiObjekt = this.ugostitelskiObjektService.findById(id);

//        byte[] encodeBase64 = Base64.getEncoder().encode(ugostitelskiObjekt.getUrlImg());
//        String base64Encode = new String(encodeBase64, "UTF-8");
//        model.addAttribute("slika", base64Encode);

        model.addAttribute("objekt", ugostitelskiObjekt);
        model.addAttribute("bodyContent", "viewObject");
        return "master-template";
    }

    @PostMapping("/rezerviraj/{id}")
    public String makeReservation(@PathVariable Long id, Model model){
//        this.ugostitelskiObjektService.rezerviraj(id);
//        model.addAttribute("objekti", this.ugostitelskiObjektService.findAll());
//        model.addAttribute("bodyContent", "home");
//        return "master-template";
        return "redirect:/home";
    }




}
