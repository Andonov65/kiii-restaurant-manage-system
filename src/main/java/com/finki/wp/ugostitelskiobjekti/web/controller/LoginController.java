package com.finki.wp.ugostitelskiobjekti.web.controller;


import com.finki.wp.ugostitelskiobjekti.Service.AuthService;
import com.finki.wp.ugostitelskiobjekti.model.User;
import com.finki.wp.ugostitelskiobjekti.model.exceptions.InvalidUserCredentialException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    //injektiranje na servicite
    public final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    //istata rabota na login servlet
    // @RequestMapping(method= RequestMethod.GET,value="")
    @GetMapping

    public String getLoginPage(Model model){
        model.addAttribute("bodyContent","login");
        return "master-template";
    }
    @PostMapping
    public String login(HttpServletRequest request, Model model){
        //vo ramkite na html kje pristapuvame do podatocitet preku model
        User user=null;
        try {
            user=this.authService.login(request.getParameter("username"),request.getParameter("password"));
            request.getSession().setAttribute("user",user);//da go stavime userot vo sesijata--ova sega go koristime kaj shopping cart
            return "redirect:/home";
        }catch (InvalidUserCredentialException exception){
            model.addAttribute("hasError",true);
            model.addAttribute("error",exception.getMessage());
            return "login";

        }
    }
}
