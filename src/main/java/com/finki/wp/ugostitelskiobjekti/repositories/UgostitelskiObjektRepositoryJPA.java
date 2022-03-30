package com.finki.wp.ugostitelskiobjekti.repositories;

import com.finki.wp.ugostitelskiobjekti.model.Shef;
import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UgostitelskiObjektRepositoryJPA extends JpaRepository<UgostitelskiObjekt, Long> {
    UgostitelskiObjekt findUgostitelskiObjektByImeNaObjekt(String ime);
    List<UgostitelskiObjekt> getAllByShef_Username(String username);
    List<UgostitelskiObjekt> getAllByShef(Shef shef);

}
