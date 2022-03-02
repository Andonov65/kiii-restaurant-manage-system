package com.finki.wp.ugostitelskiobjekti.service.impl;

import com.finki.wp.ugostitelskiobjekti.model.Grad;
import com.finki.wp.ugostitelskiobjekti.model.Shef;
import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import com.finki.wp.ugostitelskiobjekti.model.exceptions.InvalidArgumentException;
import com.finki.wp.ugostitelskiobjekti.repositories.GradRepositoryJPA;
import com.finki.wp.ugostitelskiobjekti.repositories.ShefRepositoryJPA;
import com.finki.wp.ugostitelskiobjekti.repositories.UgostitelskiObjektRepositoryJPA;
import com.finki.wp.ugostitelskiobjekti.service.UgostitelskiObjektService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class UgostitelskiObjektServiceImpl implements UgostitelskiObjektService {
    private final UgostitelskiObjektRepositoryJPA ugostitelskiObjektRepositoryJPA;
    private final GradRepositoryJPA gradRepositoryJPA;
    private final ShefRepositoryJPA shefRepositoryJPA;


    public UgostitelskiObjektServiceImpl(UgostitelskiObjektRepositoryJPA ugostitelskiObjektRepositoryJPA, GradRepositoryJPA gradRepositoryJPA, ShefRepositoryJPA shefRepositoryJPA) {
        this.ugostitelskiObjektRepositoryJPA = ugostitelskiObjektRepositoryJPA;
        this.gradRepositoryJPA = gradRepositoryJPA;
        this.shefRepositoryJPA = shefRepositoryJPA;
    }


    @Override
    @Transactional
    public UgostitelskiObjekt save(String ime, String adresa, String opis, String slika, Integer vkupnoMasi, String gradId, String shef) {
        Grad grad = this.gradRepositoryJPA.getGradByImeGrad(gradId);
        Shef shef1 = this.shefRepositoryJPA.getShefByUsername(shef);
        if(ugostitelskiObjektRepositoryJPA.findUgostitelskiObjektByImeNaObjekt(ime) != null) {
            UgostitelskiObjekt ugostitelskiObjekt = this.ugostitelskiObjektRepositoryJPA.findUgostitelskiObjektByImeNaObjekt(ime);
            ugostitelskiObjekt.setAdresa(adresa);
            ugostitelskiObjekt.setOpis(opis);
            ugostitelskiObjekt.setUrlImg(slika);
            ugostitelskiObjekt.setGrad(grad);
            ugostitelskiObjekt.setVkupnoMasi(vkupnoMasi);
            ugostitelskiObjekt.setShef(shef1);

            return ugostitelskiObjektRepositoryJPA.save(ugostitelskiObjekt);
        } else {
            UgostitelskiObjekt ugostitelskiObjekt = new UgostitelskiObjekt(ime, adresa, opis, slika, vkupnoMasi, grad, shef1);
            return ugostitelskiObjektRepositoryJPA.save(ugostitelskiObjekt);
        }
    }

    @Override
    public List<UgostitelskiObjekt> findAll() {
        return this.ugostitelskiObjektRepositoryJPA.findAll();
    }

    @Override
    public UgostitelskiObjekt findById(Long id) {
        return this.ugostitelskiObjektRepositoryJPA.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public void setPhotos(String fileName) {

    }

    @Override
    public void saveObj(String ime, String adresa, String opis, MultipartFile slika, Integer vkupnoMasi, String grad, String shef) {
        Grad gradObj = this.gradRepositoryJPA.getGradByImeGrad(grad);
        Shef shefObj = this.shefRepositoryJPA.getByUsername(shef);//.orElseThrow(InvalidArgumentException::new);
        UgostitelskiObjekt ugostitelskiObjekt = new UgostitelskiObjekt(ime, adresa, opis, vkupnoMasi, gradObj, shefObj);
        ugostitelskiObjekt.getShef();
        ugostitelskiObjekt.setShef(shefObj);
        String fileName = StringUtils.cleanPath(slika.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            ugostitelskiObjekt.setUrlImg(Base64.getEncoder().encodeToString(slika.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.ugostitelskiObjektRepositoryJPA.save(ugostitelskiObjekt);
    }


    @Override
    public UgostitelskiObjekt rezerviraj(Long id) {
         UgostitelskiObjekt ugostitelskiObjekt = findById(id);

         if(ugostitelskiObjekt.getVkupnoMasi() != 0) {
             ugostitelskiObjekt.setVkupnoMasi(ugostitelskiObjekt.getVkupnoMasi() - 1);
             this.ugostitelskiObjektRepositoryJPA.save(ugostitelskiObjekt);
         }

         return ugostitelskiObjekt;
    }
}
