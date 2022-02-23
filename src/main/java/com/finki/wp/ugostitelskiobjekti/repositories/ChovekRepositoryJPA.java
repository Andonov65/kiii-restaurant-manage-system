package com.finki.wp.ugostitelskiobjekti.repositories;

import com.finki.wp.ugostitelskiobjekti.model.Chovek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChovekRepositoryJPA extends JpaRepository<Chovek, Long> {

}
