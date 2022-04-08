package com.finki.wp.ugostitelskiobjekti.Service;


import com.finki.wp.ugostitelskiobjekti.model.Shef;
import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import com.finki.wp.ugostitelskiobjekti.model.Vraboten;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UgostitelskiObjektService {
    List<UgostitelskiObjekt> findAll();
    UgostitelskiObjekt findById(Long id);
//     UgostitelskiObjekt rezerviraj(Long id);

    void saveObj(Long id, String ime, String adresa, String opis, MultipartFile slika, Integer vkupnoMasi, String grad, String shef);

    Optional<UgostitelskiObjekt> findByEmployee(String username);

    List<UgostitelskiObjekt> findAllByShefUserName(String username);

    List<UgostitelskiObjekt> findAllByShefUserName(Shef shef) ;

    List<Vraboten> findAllEmployeesByShef(Shef shef);

    Vraboten vraboti(String username,Long objId);

    List<UgostitelskiObjekt> imeTextContaining(String text);

    void deleteUgostitelskiObjekt(Long id);

    void deleteEmpFromUgostitelskiObjekt(String username);
}
