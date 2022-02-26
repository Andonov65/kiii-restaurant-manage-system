package com.finki.wp.ugostitelskiobjekti.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Shef {

    @Id
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    public Shef() {
    }

    public Shef(String username) {
    this.username=username;
    }

    public Shef(String username, User user) {
        this.username = username;
        this.user = user;
    }

    public Shef(User user) {
        this.user = user;
    }
}
