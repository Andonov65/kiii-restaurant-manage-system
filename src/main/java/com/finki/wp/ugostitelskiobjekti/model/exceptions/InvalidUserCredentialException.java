package com.finki.wp.ugostitelskiobjekti.model.exceptions;

public class InvalidUserCredentialException extends RuntimeException{
    public InvalidUserCredentialException(){
        super("Invalid user credential exception");//prakjanje na poraka
    }
}
