package com.example.bankapplication.exceptions;

public class DuplicateAccountAlreadyExistsException extends Exception{
    public DuplicateAccountAlreadyExistsException(String message){
        super(message);
    }
}
