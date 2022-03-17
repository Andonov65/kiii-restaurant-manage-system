package com.finki.wp.ugostitelskiobjekti.repositories;

import com.finki.wp.ugostitelskiobjekti.model.Grad;
import com.finki.wp.ugostitelskiobjekti.model.Klient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlientRepositoryJPA extends JpaRepository<Klient, Long> {
    Klient findByUsername(String username);
}
