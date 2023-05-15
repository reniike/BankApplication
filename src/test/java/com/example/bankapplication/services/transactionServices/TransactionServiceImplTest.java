package com.example.bankapplication.services.transactionServices;

import com.example.bankapplication.data.models.Transaction;
import com.example.bankapplication.dtos.requests.RegisterAccountRequest;
import com.example.bankapplication.dtos.requests.TransferRequest;
import com.example.bankapplication.dtos.responses.RegisterAccountResponse;
import com.example.bankapplication.dtos.responses.TransferResponse;
import com.example.bankapplication.exceptions.*;
import com.example.bankapplication.services.accountServices.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceImplTest {

    @Autowired
    TransactionService transactionService;
    @Autowired
    AccountService accountService;
    RegisterAccountResponse registerAccountResponse;
    RegisterAccountResponse registerAccountResponse1;
    RegisterAccountResponse registerAccountResponse2;

    TransferResponse transferResponse;
    TransferResponse transferResponse1;
    TransferResponse transferResponse2;
    TransferResponse transferResponse3;

    @BeforeEach
    public void startEachWith() throws DuplicateAccountAlreadyExistsException, InsufficientFundsException, InvalidAmountException, WrongPinException, PhoneNumberAlreadyExistsException {
        accountService.deleteAll();
        transactionService.deleteAll();
        RegisterAccountRequest registerAccountRequest = new RegisterAccountRequest();
        registerAccountRequest.setFirstName("Aliyah");
        registerAccountRequest.setLastName("Babs");
        registerAccountRequest.setDateOfBirth(LocalDate.parse("2004-05-29"));
        registerAccountRequest.setGender("Female");
        registerAccountRequest.setAddress("my address");
        registerAccountRequest.setEmailAddress("aliyah@gmail.com");
        registerAccountRequest.setPhoneNumber("0904444444");
        registerAccountRequest.setPin(1234);
        registerAccountResponse = accountService.registerAccount(registerAccountRequest);


        RegisterAccountRequest registerAccountRequest1 = new RegisterAccountRequest();
        registerAccountRequest1.setFirstName("Olamiposi");
        registerAccountRequest1.setLastName("Babs");
        registerAccountRequest1.setDateOfBirth(LocalDate.parse("2004-05-29"));
        registerAccountRequest1.setGender("Female");
        registerAccountRequest1.setAddress("my address");
        registerAccountRequest1.setEmailAddress("posi@gmail.com");
        registerAccountRequest1.setPhoneNumber("0904444445");
        registerAccountRequest1.setPin(1234);
        registerAccountResponse1 = accountService.registerAccount(registerAccountRequest1);

        RegisterAccountRequest registerAccountRequest2 = new RegisterAccountRequest();
        registerAccountRequest2.setFirstName("Opemilekan");
        registerAccountRequest2.setLastName("Babs");
        registerAccountRequest2.setDateOfBirth(LocalDate.parse("2004-05-29"));
        registerAccountRequest2.setGender("Female");
        registerAccountRequest2.setAddress("my address");
        registerAccountRequest2.setEmailAddress("ope@gmail.com");
        registerAccountRequest2.setPhoneNumber("0904444446");
        registerAccountRequest2.setPin(1234);
        registerAccountResponse2 = accountService.registerAccount(registerAccountRequest2);

        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setSenderAccountNumber(registerAccountResponse.getAccountNumber());
        transferRequest.setRecipientAccountNumber(registerAccountResponse1.getAccountNumber());
        transferRequest.setAmount(BigDecimal.valueOf(200.00));
        transferRequest.setPin(1234);
        transferRequest.setDescription("Testing testing");
        transferResponse = transactionService.transfer(transferRequest);

        TransferRequest transferRequest1 = new TransferRequest();
        transferRequest1.setSenderAccountNumber(registerAccountResponse.getAccountNumber());
        transferRequest1.setRecipientAccountNumber(registerAccountResponse2.getAccountNumber());
        transferRequest1.setAmount(BigDecimal.valueOf(300.00));
        transferRequest1.setPin(1234);
        transferRequest1.setDescription("Testing testing");
        transferResponse1 = transactionService.transfer(transferRequest1);

        TransferRequest transferRequest2 = new TransferRequest();
        transferRequest2.setSenderAccountNumber(registerAccountResponse.getAccountNumber());
        transferRequest2.setRecipientAccountNumber(registerAccountResponse2.getAccountNumber());
        transferRequest2.setAmount(BigDecimal.valueOf(100.00));
        transferRequest2.setPin(1234);
        transferRequest2.setDescription("Testing testing");
        transferResponse2 = transactionService.transfer(transferRequest2);

        TransferRequest transferRequest3 = new TransferRequest();
        transferRequest3.setSenderAccountNumber(registerAccountResponse1.getAccountNumber());
        transferRequest3.setRecipientAccountNumber(registerAccountResponse.getAccountNumber());
        transferRequest3.setAmount(BigDecimal.valueOf(500.00));
        transferRequest3.setPin(1234);
        transferRequest3.setDescription("testing...");
        transferResponse3 = transactionService.transfer(transferRequest3);
    }

    @Test
    @DisplayName("Transfer test")
    public void transferTest(){
        assertEquals(BigDecimal.valueOf(900.0), transactionService.checkBalance(registerAccountResponse.getAccountNumber()));
        assertEquals(BigDecimal.valueOf(700.00 ),transactionService.checkBalance(registerAccountResponse1.getAccountNumber()));
        assertEquals(BigDecimal.valueOf(1400.00), transactionService.checkBalance(registerAccountResponse2.getAccountNumber()));
    }

    @Test
    @DisplayName("Find transaction by id")
    public void findTransactionByIdTest() throws TransactionDoesNotExistException {
        Transaction foundTransaction = transactionService.findTransactionById(transferResponse.getId());
        assertEquals(BigDecimal.valueOf(200.00), foundTransaction.getAmount());
    }

    @Test
    @DisplayName("Find all credit transaction tests")
    public void findAllCreditTransactionsTest(){
        List<Transaction> foundCreditTransactions = transactionService.findAllCreditTransactionsById(registerAccountResponse.getAccountNumber());
        assertEquals(2, foundCreditTransactions.size());
    }

    @Test
    @DisplayName("Find all debit transactions test")
    public void findAllDebitTransactionsTest(){
        List<Transaction> foundDebitTransactions = transactionService.findAllDebitTransactionsById(registerAccountResponse.getAccountNumber());
        assertEquals(3, foundDebitTransactions.size());
    }

    @Test
    @DisplayName("Transfer can't be done with the wrong pin")
    public void transferCannotBeMadeWithAWrongPin() {
        TransferRequest transferRequest4 = new TransferRequest();
        transferRequest4.setSenderAccountNumber(registerAccountResponse.getAccountNumber());
        transferRequest4.setRecipientAccountNumber(registerAccountResponse2.getAccountNumber());
        transferRequest4.setAmount(BigDecimal.valueOf(100.00));
        transferRequest4.setPin(1734);
        transferRequest4.setDescription("Testing testing");
        assertThrows(WrongPinException.class, () -> transactionService.transfer(transferRequest4));
    }

    @Test
    @DisplayName("Find balance test")
    public void findBalanceTest(){
       BigDecimal balance = transactionService.checkBalance(registerAccountResponse.getAccountNumber());
        assertEquals(BigDecimal.valueOf(900.0), balance);
    }
}