package com.finki.wp.ugostitelskiobjekti.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Shef {

    @Id
    private String username;


    public Shef() {
    }
}
