package com.example.bankapplication.exceptions;

public class PhoneNumberAlreadyExistsException extends  Exception{
    public PhoneNumberAlreadyExistsException(String message){
        super(message);
    }
}
