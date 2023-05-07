package com.example.bankapplication.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TransferResponse {
    private String id;
    private String senderAccountNumber;
    private String recipientAccountNumber;
    private LocalDateTime localDateTime;
    private String description;
}
