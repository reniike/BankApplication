package com.example.bankapplication.services.transactionServices;

import com.example.bankapplication.data.models.Account;
import com.example.bankapplication.data.models.Transaction;
import com.example.bankapplication.data.repositories.AccountRepository;
import com.example.bankapplication.data.repositories.TransactionRepository;
import com.example.bankapplication.dtos.requests.TransferRequest;
import com.example.bankapplication.dtos.responses.TransferResponse;
import com.example.bankapplication.exceptions.InsufficientFundsException;
import com.example.bankapplication.exceptions.InvalidAmountException;
import com.example.bankapplication.exceptions.TransactionDoesNotExistException;
import com.example.bankapplication.exceptions.WrongPinException;
import com.example.bankapplication.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public TransferResponse transfer(TransferRequest transferRequest) throws InsufficientFundsException, InvalidAmountException, WrongPinException {
        Transaction transaction = Mapper.map(transferRequest);

        Account recipientAccount = accountRepository.findByAccountNumber(transferRequest.getRecipientAccountNumber()).get();
        Account senderAccount = accountRepository.findByAccountNumber(transferRequest.getSenderAccountNumber()).get();
        validatePin(transferRequest.getPin(), senderAccount);
        validateThatBalanceIsNotLesserThanOne(transferRequest.getAmount(), senderAccount);
        validateThatBalanceIsSufficient(transferRequest.getAmount(), senderAccount);
        BigDecimal sender_sBalance = senderAccount.getBalance().subtract(transferRequest.getAmount());
        senderAccount.setBalance(sender_sBalance);
        accountRepository.save(senderAccount);

        BigDecimal receiver_sBalance = recipientAccount.getBalance().add(transferRequest.getAmount());
        recipientAccount.setBalance(receiver_sBalance);
        accountRepository.save(recipientAccount);

        return Mapper.map(transactionRepository.save(transaction));
    }

    @Override
    public Transaction findTransactionById(String id) throws TransactionDoesNotExistException {
        if (transactionRepository.findById(id).isEmpty()) {
            throw new TransactionDoesNotExistException("No transactions!");
        }
        return transactionRepository.findById(id).get();
    }

    @Override
    public List<Transaction> findAllCreditTransactionsById(String recipientAccountNumber) {
        List<Transaction> transactions = transactionRepository.findAllByRecipientAccountNumber(recipientAccountNumber).get();
        return transactions;
    }

    @Override
    public void deleteAll() {
        transactionRepository.deleteAll();
    }

    @Override
    public List<Transaction> findAllDebitTransactionsById(String senderAccountNumber) {
        Optional<List<Transaction>> transactions = transactionRepository.findAllBySenderAccountNumber(senderAccountNumber);
        return transactions.get();
    }

    private void validateThatBalanceIsNotLesserThanOne(BigDecimal amountToSend, Account senderAccount) throws InvalidAmountException {
        BigDecimal senderBalance = senderAccount.getBalance();
        if (senderBalance.compareTo(amountToSend) <= 0) {
            throw new InvalidAmountException("Invalid amount!");
        }
    }

    private void validateThatBalanceIsSufficient(BigDecimal amountToSend, Account senderAccount) throws InsufficientFundsException {
        BigDecimal senderBalance = senderAccount.getBalance();
        if (amountToSend.compareTo(senderBalance) <= 0) {
            throw new InsufficientFundsException("Insufficient funds!");
        }
    }

    private void validatePin(int pin, Account senderAccount) throws WrongPinException {
        int senderPin = senderAccount.getPin();
        if (senderPin != pin) {
            throw new WrongPinException("Wrong pin!");
        }
    }
}

