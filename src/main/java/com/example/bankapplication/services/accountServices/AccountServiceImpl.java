package com.example.bankapplication.services.accountServices;

import com.example.bankapplication.data.models.Account;
import com.example.bankapplication.data.repositories.AccountRepository;
import com.example.bankapplication.dtos.requests.RegisterAccountRequest;
import com.example.bankapplication.dtos.responses.RegisterAccountResponse;
import com.example.bankapplication.exceptions.DuplicateAccountAlreadyExistsException;
import com.example.bankapplication.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public RegisterAccountResponse registerAccount(RegisterAccountRequest registerAccountRequest)throws DuplicateAccountAlreadyExistsException {
        Account account = Mapper.map(registerAccountRequest);
        if (accountRepository.findByEmailAddress(account.getEmailAddress()).isPresent()){
            throw new DuplicateAccountAlreadyExistsException("Account with this email already exists!");
        }
        Account returnedAccount = accountRepository.save(account);
        return Mapper.map(returnedAccount);
    }

    @Override
    public RegisterAccountResponse findAccountByAccountNumber(String accountNumber) {
//        Account foundAccount = accountRepository.findById(accountNumber).get();
//        return Mapper.map(foundAccount);
        return Mapper.map(accountRepository.findByAccountNumber(accountNumber).get());
    }

    @Override
    public RegisterAccountResponse findByEmailAddress(String emailAddress) {
        return Mapper.map(accountRepository.findByEmailAddress(emailAddress).get());
    }

    @Override
    public void deleteAll() {
        accountRepository.deleteAll();
    }
}
