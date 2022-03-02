package com.finki.wp.ugostitelskiobjekti.Service.impl;

import com.finki.wp.ugostitelskiobjekti.Service.ShefService;
import com.finki.wp.ugostitelskiobjekti.model.Shef;
import com.finki.wp.ugostitelskiobjekti.model.exceptions.UsernameExistsException;
import com.finki.wp.ugostitelskiobjekti.repositories.ShefRepositoryJPA;
import org.springframework.stereotype.Service;



@Service
public class ShefServiceImpl implements ShefService {
    private final ShefRepositoryJPA repositoryJPA;

    public ShefServiceImpl(ShefRepositoryJPA repositoryJPA) {
        this.repositoryJPA = repositoryJPA;
    }

    @Override
    public Shef getShefByUsername(String username) {
        return this.repositoryJPA.getByUsername(username);
    }
}
