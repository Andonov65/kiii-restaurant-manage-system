package com.finki.wp.ugostitelskiobjekti.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Shef {

    @Id
    private String username;

    @OneToOne
    @MapsId
    @JoinColumn(name = "username")
    private User user;

    public Shef() {
    }
}
