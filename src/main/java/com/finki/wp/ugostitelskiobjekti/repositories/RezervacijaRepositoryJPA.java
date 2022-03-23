package com.finki.wp.ugostitelskiobjekti.repositories;

import com.finki.wp.ugostitelskiobjekti.model.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezervacijaRepositoryJPA extends JpaRepository<Rezervacija, Long> {
    void deleteAllByUgostitelskiObjektId(Long id);
}
