package com.finki.wp.ugostitelskiobjekti.Service.impl;

import com.finki.wp.ugostitelskiobjekti.model.Grad;
import com.finki.wp.ugostitelskiobjekti.model.Shef;
import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import com.finki.wp.ugostitelskiobjekti.model.Vraboten;
import com.finki.wp.ugostitelskiobjekti.repositories.GradRepositoryJPA;
import com.finki.wp.ugostitelskiobjekti.repositories.ShefRepositoryJPA;
import com.finki.wp.ugostitelskiobjekti.repositories.UgostitelskiObjektRepositoryJPA;
import com.finki.wp.ugostitelskiobjekti.Service.UgostitelskiObjektService;
import com.finki.wp.ugostitelskiobjekti.repositories.VrabotenRepositoryJPA;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UgostitelskiObjektServiceImpl implements UgostitelskiObjektService {
    private final UgostitelskiObjektRepositoryJPA ugostitelskiObjektRepositoryJPA;
    private final GradRepositoryJPA gradRepositoryJPA;
    private final ShefRepositoryJPA shefRepositoryJPA;
    private final VrabotenRepositoryJPA vrabotenRepositoryJPA;


    public UgostitelskiObjektServiceImpl(UgostitelskiObjektRepositoryJPA ugostitelskiObjektRepositoryJPA, GradRepositoryJPA gradRepositoryJPA, ShefRepositoryJPA shefRepositoryJPA, VrabotenRepositoryJPA vrabotenRepositoryJPA) {
        this.ugostitelskiObjektRepositoryJPA = ugostitelskiObjektRepositoryJPA;
        this.gradRepositoryJPA = gradRepositoryJPA;
        this.shefRepositoryJPA = shefRepositoryJPA;
        this.vrabotenRepositoryJPA = vrabotenRepositoryJPA;
    }

//    @Override
//    @Transactional
//    public UgostitelskiObjekt save(String ime, String adresa, String opis, String slika, Integer vkupnoMasi, String gradId, String shef) {
//        Grad grad = this.gradRepositoryJPA.getGradByImeGrad(gradId);
//        Shef shef1 = this.shefRepositoryJPA.getShefByUsername(shef);
//        if (ugostitelskiObjektRepositoryJPA.findUgostitelskiObjektByImeNaObjekt(ime) != null) {
//            UgostitelskiObjekt ugostitelskiObjekt = this.ugostitelskiObjektRepositoryJPA.findUgostitelskiObjektByImeNaObjekt(ime);
//            ugostitelskiObjekt.setAdresa(adresa);
//            ugostitelskiObjekt.setOpis(opis);
//            ugostitelskiObjekt.setUrlImg(slika);
//            ugostitelskiObjekt.setGrad(grad);
//            ugostitelskiObjekt.setVkupnoMasi(vkupnoMasi);
//            ugostitelskiObjekt.setShef(shef1);
//
//            return ugostitelskiObjektRepositoryJPA.save(ugostitelskiObjekt);
//        } else {
//            UgostitelskiObjekt ugostitelskiObjekt = new UgostitelskiObjekt(ime, adresa, opis, slika, vkupnoMasi, grad, shef1);
//            return ugostitelskiObjektRepositoryJPA.save(ugostitelskiObjekt);
//        }
//    }

    @Override
    public List<UgostitelskiObjekt> findAll() {
        return this.ugostitelskiObjektRepositoryJPA.findAll().stream().sorted(Comparator.comparing(UgostitelskiObjekt::getId)).collect(Collectors.toList());
    }

    @Override
    public UgostitelskiObjekt findById(Long id) {
        return this.ugostitelskiObjektRepositoryJPA.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public void setPhotos(String fileName) {

    }

    @Override
    public UgostitelskiObjekt rezerviraj(Long id) {
        UgostitelskiObjekt ugostitelskiObjekt = findById(id);

        if (ugostitelskiObjekt.getVkupnoMasi() != 0) {
            ugostitelskiObjekt.setVkupnoMasi(ugostitelskiObjekt.getVkupnoMasi() - 1);
            this.ugostitelskiObjektRepositoryJPA.save(ugostitelskiObjekt);
        }
        return ugostitelskiObjekt;
    }
    //tag + if
    @Override
    @Transactional
    public void saveObj(String ime, String adresa, String opis, MultipartFile slika, Integer vkupnoMasi, String grad, String shef) {
        Grad gradObj = this.gradRepositoryJPA.getGradByImeGrad(grad);
        Shef shefObj = this.shefRepositoryJPA.getByUsername(shef);//.orElseThrow(InvalidArgumentException::new);
        UgostitelskiObjekt ugostitelskiObjekt;
        if(this.ugostitelskiObjektRepositoryJPA.findUgostitelskiObjektByImeNaObjekt(ime)!=null){
             ugostitelskiObjekt = this.ugostitelskiObjektRepositoryJPA.findUgostitelskiObjektByImeNaObjekt(ime);
            ugostitelskiObjekt.setAdresa(adresa);
            ugostitelskiObjekt.setOpis(opis);
            ugostitelskiObjekt.setVkupnoMasi(vkupnoMasi);
            ugostitelskiObjekt.setGrad(gradObj);
            ugostitelskiObjekt.setShef(shefObj);
        }else {
            ugostitelskiObjekt = new UgostitelskiObjekt(ime, adresa, opis, vkupnoMasi, gradObj, shefObj);
            ugostitelskiObjekt.getShef();
            ugostitelskiObjekt.setShef(shefObj);
        }
        String fileName = StringUtils.cleanPath(slika.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            if(slika.isEmpty())
                ugostitelskiObjekt.setUrlImg(ugostitelskiObjekt.getUrlImg());

            ugostitelskiObjekt.setUrlImg(Base64.getEncoder().encodeToString(slika.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ugostitelskiObjektRepositoryJPA.save(ugostitelskiObjekt);
    }


    @Override
    @Transient
    public List<UgostitelskiObjekt> findAllByShefUserName(String username) {
        //    return   findAll().stream().filter(i->i.getShef().getUsername().equals(username)).collect(Collectors.toList());
        return this.ugostitelskiObjektRepositoryJPA.getAllByShef_Username(username);
    }

    @Override
    @Transactional
    public List<UgostitelskiObjekt> findAllByShefUserName(Shef shef) {
        //    return   findAll().stream().filter(i->i.getShef().getUsername().equals(username)).collect(Collectors.toList());
        // return this.ugostitelskiObjektRepositoryJPA.getAllByShef_Username(username)
        return this.ugostitelskiObjektRepositoryJPA.getAllByShef(shef);
    }
    //ako ne kje go koristime ova !
@Transactional
    @Override
    public List<Vraboten> findAllEmployeesByShef(Shef shef) {
        List<UgostitelskiObjekt> ugostitelskiObjektList = findAllByShefUserName(shef);
//        return ugostitelskiObjektList.stream().map(e -> e.getVrabotenList()).collect(Collectors.toList());
        List<Vraboten> vrabotenList = new ArrayList<>();
        ugostitelskiObjektList.stream().forEach(u -> {
            vrabotenList.addAll(u.getVrabotenList());
        });
        return vrabotenList;
    }

    @Transactional
    @Override
    public List<Vraboten> findAllEmployeesByShef2(Shef shef) {
//        List<UgostitelskiObjekt> ugostitelskiObjektList = findAllByShefUserName(shef);
//      return  ugostitelskiObjektList.stream().map(obj->{
//          if(!obj.getVrabotenList().isEmpty())
//              return obj.getVrabotenList();
//
//      })
        return null;
    }

    @Override
    public Vraboten vraboti(String username, Long objId) {
        //najdi go koj vraboten treba da se dodade
        Vraboten v = this.vrabotenRepositoryJPA.findByUsername(username);
        UgostitelskiObjekt ugostitelskiObjekt = this.ugostitelskiObjektRepositoryJPA.findById(objId).get();
        ugostitelskiObjekt.getVrabotenList().add(v);
        this.ugostitelskiObjektRepositoryJPA.save(ugostitelskiObjekt);
        return v;

    }
//    @Override
//    public List<Vraboten> findAllEmployeesByShef(Shef shef) {
//        List<UgostitelskiObjekt> ugostitelskiObjektList=findAllByShefUserName(shef);
//        return  ugostitelskiObjektList.stream().map(e->e.getVrabotenList()).collect(Collectors.toList());
//    }

}
