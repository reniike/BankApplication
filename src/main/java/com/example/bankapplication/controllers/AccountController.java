package com.example.bankapplication.controllers;

import com.example.bankapplication.dtos.requests.RegisterAccountRequest;
import com.example.bankapplication.exceptions.*;
import com.example.bankapplication.services.accountServices.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/account/register")
    public Object register(@RequestBody RegisterAccountRequest registerAccountRequest){
        try {
            return accountService.registerAccount(registerAccountRequest);
        } catch (InsufficientFundsException | InvalidAmountException | DuplicateAccountAlreadyExistsException | WrongPinException|PhoneNumberAlreadyExistsException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/account/{accountNumber}")
    public Object findAccountByAccountNumber(@PathVariable String accountNumber) {
        try {
            return accountService.findAccountByAccountNumber(accountNumber);
        } catch (AccountNumberDoesNotExistException e) {
            return e.getMessage();
        }
    }
}
