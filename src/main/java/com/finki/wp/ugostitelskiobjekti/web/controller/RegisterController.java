package com.finki.wp.ugostitelskiobjekti.web.controller;


import com.finki.wp.ugostitelskiobjekti.Service.AuthService;
import com.finki.wp.ugostitelskiobjekti.Service.UserService;
import com.finki.wp.ugostitelskiobjekti.model.Role;
import com.finki.wp.ugostitelskiobjekti.model.exceptions.InvalidArgumentException;
import com.finki.wp.ugostitelskiobjekti.model.exceptions.PasswordDoNotMatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    //ima potreba od get maping zatoa shto mora nekako da ja vratime stranata
    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role,
                           @RequestParam String phoneNum) {
        //prvo gledame vo authService
        //zatoa shto mora parametrite da gi pratime kaj authservice gore go injektiravme
        try {
         this.userService.register(username, password, repeatedPassword, name, surname,role,phoneNum);
            return "redirect:/login";//go redirectirame kon login otkako se registriral
        }catch (InvalidArgumentException | PasswordDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }

    }
}
