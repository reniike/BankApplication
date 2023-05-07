package com.example.bankapplication.data.repositories;

import com.example.bankapplication.data.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    Optional<List<Transaction>> findAllByRecipientAccountNumber(String recipientAccountNumber);

   Optional<List<Transaction>> findAllBySenderAccountNumber(String senderAccountNumber);
}
