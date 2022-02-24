package com.finki.wp.ugostitelskiobjekti.web.controller;

import com.finki.wp.ugostitelskiobjekti.Service.AuthService;
import com.finki.wp.ugostitelskiobjekti.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//shef=sopstvenik na lokal!
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
                              @RequestParam String ime,
                              @RequestParam String adresa,
                              @RequestParam String opis,
                              @RequestParam String slika,
                              @RequestParam Integer vkupnoMasi) {


        return "redirect:/master-template";
    }

    //dodavanje na vraboten vo baza
    //sopstvenikot za svoite vraboteni gi smisluva lozinkite i korisnichkite iminja
    //kelnerPrvaSmena -->pass
    ///admin/addEmployee
    @GetMapping("/addEmployee")
    public String getAddEmployeeForm(Model model) {
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

//    @PostMapping("/addEmployee")
//    public String getAddEmployeeForm(Model model,
//                                     @RequestParam String name ,
//                                     @RequestParam String surname,
//                                     @RequestParam String phoneNum,
//                                     @RequestParam String username,
//                                     @RequestParam Integer password,
//                                     @RequestParam String imeObj) {
//
//        return "master-template";
//    }



}
