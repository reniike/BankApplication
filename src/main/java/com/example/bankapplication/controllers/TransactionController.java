package com.example.bankapplication.controllers;

import com.example.bankapplication.dtos.requests.TransferRequest;
import com.example.bankapplication.exceptions.InsufficientFundsException;
import com.example.bankapplication.exceptions.InvalidAmountException;
import com.example.bankapplication.exceptions.WrongPinException;
import com.example.bankapplication.services.transactionServices.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("/transaction/{accountNumber}")
    public Object checkBalance(@PathVariable String accountNumber){
        try {
            return transactionService.checkBalance(accountNumber);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/transaction/transfer")
    public Object transfer(@RequestBody TransferRequest transferRequest){
        try {
            return  transactionService.transfer(transferRequest);
        } catch (InsufficientFundsException | WrongPinException | InvalidAmountException e) {
           return e.getMessage();
        }
    }

}
