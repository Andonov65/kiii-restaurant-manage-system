package com.finki.wp.ugostitelskiobjekti.repositories;

import com.finki.wp.ugostitelskiobjekti.model.Chovek;
import com.finki.wp.ugostitelskiobjekti.model.Grad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradRepositoryJPA extends JpaRepository<Grad, Long> {
}
