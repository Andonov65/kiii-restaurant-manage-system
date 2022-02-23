package com.finki.wp.ugostitelskiobjekti.Service;


import com.finki.wp.ugostitelskiobjekti.model.Role;
import com.finki.wp.ugostitelskiobjekti.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    //se povikuva za da se otkrie dali nekoj korisnik postoi vo nashata baza,def vo UserDetailsService
   // public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
    User register(String username, String password, String repeatpassword, String name, String surname, Role role,String phoneNum);

}
