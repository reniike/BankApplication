package com.example.bankapplication.services.accountServices;

import com.example.bankapplication.data.models.Account;
import com.example.bankapplication.data.repositories.AccountRepository;
import com.example.bankapplication.data.repositories.TransactionRepository;
import com.example.bankapplication.dtos.requests.RegisterAccountRequest;
import com.example.bankapplication.dtos.requests.TransferRequest;
import com.example.bankapplication.dtos.responses.RegisterAccountResponse;
import com.example.bankapplication.exceptions.DuplicateAccountAlreadyExistsException;
import com.example.bankapplication.exceptions.InsufficientFundsException;
import com.example.bankapplication.exceptions.InvalidAmountException;
import com.example.bankapplication.exceptions.WrongPinException;
import com.example.bankapplication.services.transactionServices.TransactionService;
import com.example.bankapplication.services.transactionServices.TransactionServiceImpl;
import com.example.bankapplication.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public RegisterAccountResponse registerAccount(RegisterAccountRequest registerAccountRequest) throws DuplicateAccountAlreadyExistsException, InsufficientFundsException, InvalidAmountException, WrongPinException {
        Account account = Mapper.map(registerAccountRequest);
        if (accountRepository.findByEmailAddress(account.getEmailAddress()).isPresent()) throw new DuplicateAccountAlreadyExistsException("Account with this email already exists!");
        Account returnedAccount = accountRepository.save(account);
        TransferRequest transferRequest = new TransferRequest();
        String senderAccount = TransactionServiceImpl.BANK_ACCOUNT_NUMBER;
        Account recipientAccount = accountRepository.findByAccountNumber(returnedAccount.getAccountNumber()).get();
        transferRequest.setSenderAccountNumber(senderAccount);
        transferRequest.setRecipientAccountNumber(recipientAccount.getAccountNumber());
        transferRequest.setAmount(BigDecimal.valueOf(1000));
        transferRequest.setDescription("Registration bonus!");
        transactionRepository.save(Mapper.map(transferRequest));
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
