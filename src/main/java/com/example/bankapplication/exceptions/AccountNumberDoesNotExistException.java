package com.example.bankapplication.exceptions;

public class AccountNumberDoesNotExistException extends Exception{
    public AccountNumberDoesNotExistException(String message){
        super(message);
    }
}
