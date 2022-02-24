package com.finki.wp.ugostitelskiobjekti.service;


import com.finki.wp.ugostitelskiobjekti.model.UgostitelskiObjekt;

public interface UgostitelskiObjektService {

    UgostitelskiObjekt save(String ime, String adresa, String opis, String slika, Integer vkupnoMasi, String grad, String shef);
}
