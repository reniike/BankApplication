package com.example.bankapplication.dtos.requests;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class TransferRequest {
   private String senderAccountNumber;
   private String recipientAccountNumber;
   private BigDecimal amount;
   private int pin;
   private String description;

}
