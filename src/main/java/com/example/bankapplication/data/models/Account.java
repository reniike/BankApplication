package com.example.bankapplication.data.models;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Account")
public class Account {
    @Id
    private String accountNumber;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String gender;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private BigDecimal balance;
    private int pin;
}
