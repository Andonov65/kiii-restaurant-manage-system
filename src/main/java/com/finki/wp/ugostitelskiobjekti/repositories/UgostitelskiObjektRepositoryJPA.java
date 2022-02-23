package com.finki.wp.ugostitelskiobjekti.repositories;

import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UgostitelskiObjektRepositoryJPA extends JpaRepository<UgostitelskiObjekt, Long> {
}
