package com.example.bankapplication.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegisterAccountResponse {
    private String accountNumber;
    private String fullName;
    private BigDecimal balance;
    private int pin;

}
