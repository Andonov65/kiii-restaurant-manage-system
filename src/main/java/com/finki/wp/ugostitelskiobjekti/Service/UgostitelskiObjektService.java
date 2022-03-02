package com.finki.wp.ugostitelskiobjekti.Service;


import com.finki.wp.ugostitelskiobjekti.model.Shef;
import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UgostitelskiObjektService {

    UgostitelskiObjekt save(String ime, String adresa, String opis, String slika, Integer vkupnoMasi, String grad, String shef);
    List<UgostitelskiObjekt> findAll();
    UgostitelskiObjekt findById(Long id);
    public UgostitelskiObjekt rezerviraj(Long id);
    void setPhotos(String fileName);

    void saveObj(String ime, String adresa, String opis, MultipartFile slika, Integer vkupnoMasi, String grad, String shef);

    List<UgostitelskiObjekt> findAllByShefUserName(String username);


    List<UgostitelskiObjekt> findAllByShefUserName(Shef shef) ;
}
