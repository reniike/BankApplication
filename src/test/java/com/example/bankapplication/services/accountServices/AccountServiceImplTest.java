package com.example.bankapplication.services.accountServices;

import com.example.bankapplication.dtos.requests.RegisterAccountRequest;
import com.example.bankapplication.dtos.responses.RegisterAccountResponse;
import com.example.bankapplication.exceptions.DuplicateAccountAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AccountServiceImplTest {
    @Autowired
    private AccountService accountService;
    private RegisterAccountRequest registerAccountRequest;
    private RegisterAccountResponse registerAccountResponse;

    @BeforeEach
    public void startEachWith() throws DuplicateAccountAlreadyExistsException {
        accountService.deleteAll();
        registerAccountRequest = new RegisterAccountRequest();
        registerAccountRequest.setFirstName("My first name");
        registerAccountRequest.setLastName("My last name");
        registerAccountRequest.setDateOfBirth(LocalDate.parse("2004-05-29"));
        registerAccountRequest.setGender("Female");
        registerAccountRequest.setAddress("my address");
        registerAccountRequest.setEmailAddress("renny@gmail.com"); //validate.
        registerAccountRequest.setPhoneNumber("0904444444");
        registerAccountRequest.setPin(2000);
        registerAccountResponse = accountService.registerAccount(registerAccountRequest);

    }

    @Test
    @DisplayName("Register/create account test")
    public void testThatAccountCanBeRegistered(){
        assertEquals("904444444", registerAccountResponse.getAccountNumber());
    }

    @Test
    @DisplayName("Test that account is credited with a joining bonus of #1000.00 naira")
    public void testThatAccountIsCreditedWithAJoiningBonusOfOneThousandNaira(){
        assertEquals(BigDecimal.valueOf(1000.00), registerAccountResponse.getBalance());
    }

    @Test
    @DisplayName("Find account by account number")
    public void findAccountTest(){
        assertEquals(registerAccountResponse, accountService.findAccountByAccountNumber("904444444"));
    }

    @Test
    @DisplayName("Find account by email address")
    public void findAccountByEmailAddress(){
        assertEquals(registerAccountResponse, accountService.findByEmailAddress("renny@gmail.com"));
    }

    @Test
    @DisplayName("Test that two accounts with the same email cannot register")
    public void testThatTwoAccountsWithTheSameEmailCannotRegister(){
       RegisterAccountRequest registerAccountRequest1 = new RegisterAccountRequest();
        registerAccountRequest1.setFirstName("My first name");
        registerAccountRequest1.setLastName("My last name");
        registerAccountRequest1.setDateOfBirth(LocalDate.parse("2004-05-29"));
        registerAccountRequest1.setGender("Female");
        registerAccountRequest1.setAddress("my address");
        registerAccountRequest1.setEmailAddress("renny@gmail.com"); //validate.
        registerAccountRequest1.setPhoneNumber("0904444445");
        registerAccountRequest1.setPin(1234);
        assertThrows(DuplicateAccountAlreadyExistsException.class, () -> accountService.registerAccount(registerAccountRequest1));
    }
}