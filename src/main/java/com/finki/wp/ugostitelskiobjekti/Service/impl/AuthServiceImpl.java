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

}
