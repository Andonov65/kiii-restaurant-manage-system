package com.finki.wp.ugostitelskiobjekti.Service;

import com.finki.wp.ugostitelskiobjekti.model.Shef;
import org.springframework.stereotype.Service;

@Service
public interface ShefService {
    Shef getShefByUsername(String username);
}
