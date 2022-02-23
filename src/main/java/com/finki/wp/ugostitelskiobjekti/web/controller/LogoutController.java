package com.finki.wp.ugostitelskiobjekti.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    @GetMapping
    public String logout(HttpServletRequest request){
        //da ni nade redirect kon nazad
        //kje treba da ja zememe sesijata za da ja invalidirame
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
