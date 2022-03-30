package com.finki.wp.ugostitelskiobjekti.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Grad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imeGrad;



    public Grad() {
    }

    public Grad(String imeGrad) {
        this.imeGrad = imeGrad;
    }
}
