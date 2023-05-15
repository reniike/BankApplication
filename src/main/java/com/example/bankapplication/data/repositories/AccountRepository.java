package com.example.bankapplication.data.repositories;

import com.example.bankapplication.data.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Map;
import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByEmailAddress(String emailAddress);

    Optional<Object> findByPhoneNumber(String phoneNumber);
}
