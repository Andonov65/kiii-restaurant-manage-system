package com.finki.wp.ugostitelskiobjekti.Service.impl;


import com.finki.wp.ugostitelskiobjekti.Service.AuthService;
import com.finki.wp.ugostitelskiobjekti.model.User;
import com.finki.wp.ugostitelskiobjekti.model.exceptions.InvalidArgumentException;
import com.finki.wp.ugostitelskiobjekti.model.exceptions.InvalidUserCredentialException;
import com.finki.wp.ugostitelskiobjekti.repositories.UserRepositoryJPA;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepositoryJPA userRepository;//pri smaiot start se pravi ova

    public AuthServiceImpl(UserRepositoryJPA userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentException();
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialException::new);
        //ako ne postoi takov korisnik togahs neka se frli nekoj exception shto soodvestuva
    }
//so prethodnata implementacija se dozvoluvashe noviot shto saka so toa username da si napravi acc so toa
    //shto kje go izbrishe stariot
    //sega sakame da go informirame deka already exists so takvo username
//prethodno se vrsheshe registracija preku ova pred da se koristi Custom Authentication
//    @Override
//    public User register(String username, String password, String repeatpassword, String name, String surname) {
//        if (username == null || username.isEmpty() || password == null || password.isEmpty())
//            throw new InvalidArgumentException();
//        //mora vo samata biznis logika da proverime za pass-ot
//        if (!password.equals(repeatpassword)) {
//            throw new PasswordDoNotMatchException();
//        }
//
//        if (this.userRepository.findByUsername(username).isPresent() ) {
//            throw new UsernameExistsException(username);
//        }
//        User user = new User(username, password, name, surname);
//        userRepository.save(user);//save metod od jpa repository
//        return user;
//    }
}
