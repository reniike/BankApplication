package com.example.bankapplication.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document("Transaction")
public class Transaction{
    @Id
    private String id;
    private String senderAccountNumber;
    private String recipientAccountNumber;
    private LocalDateTime localDateTime;
    private BigDecimal amount;
    private String description;
    private int transactionPin;
}
