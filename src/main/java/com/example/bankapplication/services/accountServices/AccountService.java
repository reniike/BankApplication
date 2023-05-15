package com.example.bankapplication.services.accountServices;

import com.example.bankapplication.data.models.Account;
import com.example.bankapplication.dtos.requests.RegisterAccountRequest;
import com.example.bankapplication.dtos.responses.RegisterAccountResponse;
import com.example.bankapplication.exceptions.*;

import java.math.BigDecimal;

public interface AccountService {
    RegisterAccountResponse registerAccount(RegisterAccountRequest registerAccountRequest) throws DuplicateAccountAlreadyExistsException, InsufficientFundsException, InvalidAmountException, WrongPinException, PhoneNumberAlreadyExistsException;

    RegisterAccountResponse findAccountByAccountNumber(String accountNumber) throws AccountNumberDoesNotExistException;

    RegisterAccountResponse findByEmailAddress(String emailAddress);

    void deleteAll();


}
