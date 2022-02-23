package com.finki.wp.ugostitelskiobjekti.repositories;

import com.finki.wp.ugostitelskiobjekti.model.Grad;
import com.finki.wp.ugostitelskiobjekti.model.Vraboten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VrabotenRepositoryJPA extends JpaRepository<Vraboten, Long> {
}
