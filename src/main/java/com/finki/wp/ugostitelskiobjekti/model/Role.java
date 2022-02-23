package com.finki.wp.ugostitelskiobjekti.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_USER, ROLE_ADMIN, ROLE_EMPLOYEE;
    //bez ova ne raboteshe kaj User klasata metodot  getAuthorities()
    @Override
    public String getAuthority() {
        return name();//vrakja edno od gore navedenite
    }
}
