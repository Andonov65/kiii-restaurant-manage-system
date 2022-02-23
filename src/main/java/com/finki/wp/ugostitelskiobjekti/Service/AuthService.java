package com.finki.wp.ugostitelskiobjekti.Service;


import com.finki.wp.ugostitelskiobjekti.model.User;

public interface AuthService {
    User login(String username, String password);

}
