package com.finki.wp.ugostitelskiobjekti.model.exceptions;

public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException(String username){
        super(String.format("User with username:%s already exists",username));
    }
}
