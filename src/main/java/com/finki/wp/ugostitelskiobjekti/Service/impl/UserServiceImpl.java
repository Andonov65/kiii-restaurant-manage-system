package com.finki.wp.ugostitelskiobjekti.Service.impl;


import com.finki.wp.ugostitelskiobjekti.Service.UserService;
import com.finki.wp.ugostitelskiobjekti.model.Role;
import com.finki.wp.ugostitelskiobjekti.model.User;
import com.finki.wp.ugostitelskiobjekti.model.exceptions.InvalidArgumentException;
import com.finki.wp.ugostitelskiobjekti.model.exceptions.PasswordDoNotMatchException;
import com.finki.wp.ugostitelskiobjekti.model.exceptions.UsernameExistsException;
import com.finki.wp.ugostitelskiobjekti.repositories.UserRepositoryJPA;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    //zavisnost od user repository
    private final UserRepositoryJPA userRepository;
    //dopolnitelno i za pass passfordEncoder
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepositoryJPA userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //implementacijata od AuthServiceImpl
    @Override
    public User register(String username, String password, String repeatpassword, String name, String surname, Role role,String phoneNum) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidArgumentException();
        //mora vo samata biznis logika da proverime za pass-ot
        if (!password.equals(repeatpassword)){
            throw new PasswordDoNotMatchException();
        }

        if(this.userRepository.findByUsername(username).isPresent()){
            throw new UsernameExistsException(username);
        }
        //ne mi beshe encode
        User user = new User(username,passwordEncoder.encode(password),name,surname,role,phoneNum)   ;
        userRepository.save(user);//save metod od jpa repository
        return user;
    }
//proverka dali korisnikot voopshto postou

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }
}
