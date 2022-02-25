package com.finki.wp.ugostitelskiobjekti.service.impl;

import com.finki.wp.ugostitelskiobjekti.model.Grad;
import com.finki.wp.ugostitelskiobjekti.repositories.GradRepositoryJPA;
import com.finki.wp.ugostitelskiobjekti.service.GradService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradServiceImpl implements GradService {
    private final GradRepositoryJPA gradRepositoryJPA;

    public GradServiceImpl(GradRepositoryJPA gradRepositoryJPA) {
        this.gradRepositoryJPA = gradRepositoryJPA;
    }

    @Override
    public List<Grad> findAll() {
        return this.gradRepositoryJPA.findAll();
    }
}
