package com.example.bankapplication.services.accountServices;

import com.example.bankapplication.data.models.Account;
import com.example.bankapplication.dtos.requests.RegisterAccountRequest;
import com.example.bankapplication.dtos.responses.RegisterAccountResponse;
import com.example.bankapplication.exceptions.DuplicateAccountAlreadyExistsException;
import com.example.bankapplication.exceptions.InsufficientFundsException;
import com.example.bankapplication.exceptions.InvalidAmountException;
import com.example.bankapplication.exceptions.WrongPinException;

import java.math.BigDecimal;

public interface AccountService {
    RegisterAccountResponse registerAccount(RegisterAccountRequest registerAccountRequest) throws DuplicateAccountAlreadyExistsException, InsufficientFundsException, InvalidAmountException, WrongPinException;

    RegisterAccountResponse findAccountByAccountNumber(String accountNumber);

    RegisterAccountResponse findByEmailAddress(String emailAddress);

    void deleteAll();


}
