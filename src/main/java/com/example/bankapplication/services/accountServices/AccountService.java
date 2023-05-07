package com.example.bankapplication.services.accountServices;

import com.example.bankapplication.data.models.Account;
import com.example.bankapplication.dtos.requests.RegisterAccountRequest;
import com.example.bankapplication.dtos.responses.RegisterAccountResponse;
import com.example.bankapplication.exceptions.DuplicateAccountAlreadyExistsException;

public interface AccountService {
    RegisterAccountResponse registerAccount(RegisterAccountRequest registerAccountRequest)throws DuplicateAccountAlreadyExistsException;

    RegisterAccountResponse findAccountByAccountNumber(String accountNumber);

    RegisterAccountResponse findByEmailAddress(String emailAddress);

    void deleteAll();
}
