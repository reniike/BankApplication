package com.example.bankapplication.services.transactionServices;

import com.example.bankapplication.data.models.Transaction;
import com.example.bankapplication.dtos.requests.TransferRequest;
import com.example.bankapplication.dtos.responses.TransferResponse;
import com.example.bankapplication.exceptions.InsufficientFundsException;
import com.example.bankapplication.exceptions.InvalidAmountException;
import com.example.bankapplication.exceptions.TransactionDoesNotExistException;
import com.example.bankapplication.exceptions.WrongPinException;
import java.util.List;

public interface TransactionService {
    TransferResponse transfer(TransferRequest transferRequest) throws InsufficientFundsException, InvalidAmountException, WrongPinException;

    Transaction findTransactionById(String id) throws TransactionDoesNotExistException;

    List<Transaction> findAllCreditTransactionsById(String recipientAccountNumber);

    void deleteAll();

    List<Transaction> findAllDebitTransactionsById(String senderAccountNumber);
}
