package com.finki.wp.ugostitelskiobjekti.Service.impl;


import com.finki.wp.ugostitelskiobjekti.Service.UserService;
import com.finki.wp.ugostitelskiobjekti.model.*;
import com.finki.wp.ugostitelskiobjekti.model.exceptions.InvalidArgumentException;
import com.finki.wp.ugostitelskiobjekti.model.exceptions.PasswordDoNotMatchException;
import com.finki.wp.ugostitelskiobjekti.model.exceptions.UsernameExistsException;
import com.finki.wp.ugostitelskiobjekti.repositories.KlientRepositoryJPA;
import com.finki.wp.ugostitelskiobjekti.repositories.ShefRepositoryJPA;
import com.finki.wp.ugostitelskiobjekti.repositories.UserRepositoryJPA;
import com.finki.wp.ugostitelskiobjekti.repositories.VrabotenRepositoryJPA;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    //zavisnost od user repository
    private final UserRepositoryJPA userRepository;
    //dopolnitelno i za pass passfordEncoder
    private final PasswordEncoder passwordEncoder;


    private final ShefRepositoryJPA shefRepository;
    private final VrabotenRepositoryJPA vrabotenRepository;
    private final KlientRepositoryJPA klientRepository;

    public UserServiceImpl(UserRepositoryJPA userRepository, PasswordEncoder passwordEncoder, ShefRepositoryJPA shefRepository, VrabotenRepositoryJPA vrabotenRepository, KlientRepositoryJPA klientRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;


        this.shefRepository = shefRepository;
        this.vrabotenRepository = vrabotenRepository;
        this.klientRepository = klientRepository;
    }

    //implementacijata od AuthServiceImpl
    @Override
    public User register(String username, String password, String repeatpassword, String name, String surname, Role role, String phoneNum) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentException();
        //mora vo samata biznis logika da proverime za pass-ot
        if (!password.equals(repeatpassword)) {
            throw new PasswordDoNotMatchException();
        }

        if (this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameExistsException(username);
        }
        //ne mi beshe encode

        //ovde porano beshe
        User user = new User(username, passwordEncoder.encode(password), name, surname, role, phoneNum);
        userRepository.save(user);//save metod od jpa repository

        //gi zachuvavme i kako objekti !
        if (role.equals(Role.ROLE_ADMIN)) {
            //  Shef shefUser=new Shef(username,user);
            // Shef shefUser=new Shef(username);
            Shef shefUser = new Shef();
            shefUser.setUser(user);
            shefUser.setUsername(username);
            this.shefRepository.save(shefUser);

        } else if (role.equals(Role.ROLE_EMPLOYEE)) {
            Vraboten vrabotenUser = new Vraboten();
            vrabotenUser.setUsername(username);
            vrabotenUser.setDatumNaVrabotuvanje(LocalDate.now());
            vrabotenUser.setUser(user);
            this.vrabotenRepository.save(vrabotenUser);
        } else {
            Klient klient = new Klient();
            klient.setUsername(username);
            klient.setUser(user);
            this.klientRepository.save(klient);
        }

        return user;
    }
//proverka dali korisnikot voopshto postou

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }
}
