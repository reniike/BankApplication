package com.example.bankapplication.exceptions;

public class TransactionDoesNotExistException extends Exception{
    public TransactionDoesNotExistException(String message){
        super(message);
    }
}
