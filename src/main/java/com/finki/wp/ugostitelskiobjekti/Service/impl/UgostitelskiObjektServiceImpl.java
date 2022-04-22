package com.finki.wp.ugostitelskiobjekti.Service.impl;

import com.finki.wp.ugostitelskiobjekti.model.Grad;
import com.finki.wp.ugostitelskiobjekti.model.Shef;
import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;
import com.finki.wp.ugostitelskiobjekti.model.Vraboten;
import com.finki.wp.ugostitelskiobjekti.repositories.*;
import com.finki.wp.ugostitelskiobjekti.Service.UgostitelskiObjektService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UgostitelskiObjektServiceImpl implements UgostitelskiObjektService {
    private final UgostitelskiObjektRepositoryJPA ugostitelskiObjektRepositoryJPA;
    private final GradRepositoryJPA gradRepositoryJPA;
    private final ShefRepositoryJPA shefRepositoryJPA;
    private final VrabotenRepositoryJPA vrabotenRepositoryJPA;
    private final RezervacijaRepositoryJPA rezervacijaRepositoryJPA;
    private final UserRepositoryJPA userRepositoryJPA;


    public UgostitelskiObjektServiceImpl(UgostitelskiObjektRepositoryJPA ugostitelskiObjektRepositoryJPA, GradRepositoryJPA gradRepositoryJPA, ShefRepositoryJPA shefRepositoryJPA, VrabotenRepositoryJPA vrabotenRepositoryJPA, RezervacijaRepositoryJPA rezervacijaRepositoryJPA, UserRepositoryJPA userRepositoryJPA) {
        this.ugostitelskiObjektRepositoryJPA = ugostitelskiObjektRepositoryJPA;
        this.gradRepositoryJPA = gradRepositoryJPA;
        this.shefRepositoryJPA = shefRepositoryJPA;
        this.vrabotenRepositoryJPA = vrabotenRepositoryJPA;
        this.rezervacijaRepositoryJPA = rezervacijaRepositoryJPA;
        this.userRepositoryJPA = userRepositoryJPA;
    }


    @Override
    public List<UgostitelskiObjekt> findAll() {
        return this.ugostitelskiObjektRepositoryJPA.findAll().stream().sorted(Comparator.comparing(UgostitelskiObjekt::getId)).collect(Collectors.toList());
    }

    @Override
    public UgostitelskiObjekt findById(Long id) {
        return this.ugostitelskiObjektRepositoryJPA.findById(id).orElseThrow(() -> new RuntimeException());
    }



//    @Override
//    public UgostitelskiObjekt rezerviraj(Long id) {
//        UgostitelskiObjekt ugostitelskiObjekt = findById(id);
//
//        if (ugostitelskiObjekt.getVkupnoMasi() != 0) {
//            ugostitelskiObjekt.setVkupnoMasi(ugostitelskiObjekt.getVkupnoMasi() - 1);
//            this.ugostitelskiObjektRepositoryJPA.save(ugostitelskiObjekt);
//        }
//        return ugostitelskiObjekt;
//    }
    //tag + if
    @Override
    @Transactional
    public void saveObj(Long id, String ime, String adresa, String opis, MultipartFile slika, Integer vkupnoMasi, String grad, String shef) {
        Grad gradObj = this.gradRepositoryJPA.getGradByImeGrad(grad);
        Shef shefObj = this.shefRepositoryJPA.getByUsername(shef);//.orElseThrow(InvalidArgumentException::new);
        UgostitelskiObjekt ugostitelskiObjekt = null;

        if(id!=null) {
            if (this.ugostitelskiObjektRepositoryJPA.findById(id).isPresent()) {
                ugostitelskiObjekt = this.ugostitelskiObjektRepositoryJPA.findById(id).orElseThrow(RuntimeException::new);
                ugostitelskiObjekt.setImeNaObjekt(ime);
                ugostitelskiObjekt.setAdresa(adresa);
                ugostitelskiObjekt.setOpis(opis);
                ugostitelskiObjekt.setVkupnoMasi(vkupnoMasi);
                ugostitelskiObjekt.setGrad(gradObj);
                ugostitelskiObjekt.setShef(shefObj);
            }
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
    public Optional<UgostitelskiObjekt> findByEmployee(String username) {
        return this.ugostitelskiObjektRepositoryJPA.findAll()
                .stream().filter(i -> i.getVrabotenList().stream().anyMatch(o -> o.getUsername().equals(username)))
                .findFirst();
    }


    @Override
    @Transient
    public List<UgostitelskiObjekt> findAllByShefUserName(String username) {
        return this.ugostitelskiObjektRepositoryJPA.getAllByShef_Username(username);

    }

    @Override
    @Transactional
    public List<UgostitelskiObjekt> findAllByShefUserName(Shef shef) {
       return this.ugostitelskiObjektRepositoryJPA.getAllByShef(shef);
    }
    //ako ne kje go koristime ova !
    @Transactional
    @Override
    public List<Vraboten> findAllEmployeesByShef(Shef shef) {
        List<UgostitelskiObjekt> ugostitelskiObjektList = findAllByShefUserName(shef);

        List<Vraboten> vrabotenList = new ArrayList<>();
        ugostitelskiObjektList.stream().forEach(u -> {
            vrabotenList.addAll(u.getVrabotenList());
        });
        return vrabotenList;
    }


    @Override
    public Vraboten vraboti(String username, Long objId) {
        //najdi go koj vraboten treba da se dodade
        Vraboten v = this.vrabotenRepositoryJPA.findByUsername(username);
        UgostitelskiObjekt ugostitelskiObjekt = this.ugostitelskiObjektRepositoryJPA.findById(objId).get();
        ugostitelskiObjekt.getVrabotenList().add(v);
        v.setUgostitelskiObjekt(ugostitelskiObjekt);
        this.vrabotenRepositoryJPA.save(v);
        this.ugostitelskiObjektRepositoryJPA.save(ugostitelskiObjekt);
        return v;

    }

    @Override
    public List<UgostitelskiObjekt> imeTextContaining(String text) {
       return this.ugostitelskiObjektRepositoryJPA.findAll()
               .stream().filter(i -> i.getImeNaObjekt().toLowerCase().contains(text.toLowerCase()))
               .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteUgostitelskiObjekt(Long id) {

        UgostitelskiObjekt ugostitelskiObjektList =this.ugostitelskiObjektRepositoryJPA.findAll()
                .stream().filter(i -> i.getId().equals(id)).findFirst().orElseThrow(RuntimeException::new);


           this.ugostitelskiObjektRepositoryJPA.findById(ugostitelskiObjektList.getId())
                   .get().getRezervacijaList().clear();
//            this.ugostitelskiObjektRepositoryJPA.findById(ugostitelskiObjektList.getId())
//                    .get().getVrabotenList().clear();



            rezervacijaRepositoryJPA.deleteAllByUgostitelskiObjektId(ugostitelskiObjektList.getId());


        ugostitelskiObjektList.getVrabotenList()
                        .forEach(o ->{
                            userRepositoryJPA.deleteByUsername(o.getUsername());
                                    vrabotenRepositoryJPA.deleteByUsername(o.getUsername());
                        } );


        this.ugostitelskiObjektRepositoryJPA.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteEmpFromUgostitelskiObjekt(String username) {
        this.ugostitelskiObjektRepositoryJPA
                .findAll()
                .forEach(i -> i.getVrabotenList()
                        .removeIf(o ->
                                o.getUsername().equals(username)));


        this.vrabotenRepositoryJPA.deleteByUsername(username);

        this.userRepositoryJPA.deleteByUsername(username);
    }


}
