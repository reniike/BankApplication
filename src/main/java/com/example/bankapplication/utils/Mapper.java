package com.example.bankapplication.utils;

import com.example.bankapplication.data.models.Account;
import com.example.bankapplication.data.models.Transaction;
import com.example.bankapplication.dtos.requests.RegisterAccountRequest;
import com.example.bankapplication.dtos.requests.TransferRequest;
import com.example.bankapplication.dtos.responses.RegisterAccountResponse;
import com.example.bankapplication.dtos.responses.TransferResponse;

public class Mapper {
    public static Account map(RegisterAccountRequest registerAccountRequest) {
        Account account = new Account();
        account.setFirstName(registerAccountRequest.getFirstName());
        account.setLastName(registerAccountRequest.getLastName());
        account.setGender(registerAccountRequest.getGender());
        account.setEmailAddress(registerAccountRequest.getEmailAddress());
        account.setAccountNumber(registerAccountRequest.getPhoneNumber().substring(1));
        account.setDateOfBirth(registerAccountRequest.getDateOfBirth());
        account.setPhoneNumber(registerAccountRequest.getPhoneNumber());
        account.setPin(registerAccountRequest.getPin());
        account.setAddress(registerAccountRequest.getAddress());
        return account;
    }

    public static RegisterAccountResponse map(Account returnedAccount) {
        RegisterAccountResponse accountResponse = new RegisterAccountResponse();
        accountResponse.setFullName(returnedAccount.getFirstName() + " " + returnedAccount.getLastName());
        accountResponse.setAccountNumber(returnedAccount.getAccountNumber());
        return accountResponse;
    }

    public static Transaction map(TransferRequest transferRequest){
        Transaction transaction = new Transaction();
        transaction.setSenderAccountNumber(transferRequest.getSenderAccountNumber());
        transaction.setRecipientAccountNumber(transferRequest.getRecipientAccountNumber());
        transaction.setDescription(transferRequest.getDescription());
        transaction.setTransactionPin(transferRequest.getPin());
        transaction.setAmount(transferRequest.getAmount());
        return transaction;
    }


    public static TransferResponse map(Transaction transaction) {
        TransferResponse transferResponse = new TransferResponse();
        transferResponse.setId(transaction.getId());
        transferResponse.setDescription(transaction.getDescription());
        transferResponse.setLocalDateTime(transaction.getLocalDateTime());
        transferResponse.setSenderAccountNumber(transaction.getSenderAccountNumber());
        transferResponse.setRecipientAccountNumber(transaction.getRecipientAccountNumber());
        return transferResponse;
    }
}
