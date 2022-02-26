package com.finki.wp.ugostitelskiobjekti.repositories;

import com.finki.wp.ugostitelskiobjekti.model.Grad;
import com.finki.wp.ugostitelskiobjekti.model.Shef;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShefRepositoryJPA extends JpaRepository<Shef, Long> {
    Shef getShefByUsername(String username);
    Shef getByUsername(String username);

   Optional<Shef> findByUsername(String username);
}
