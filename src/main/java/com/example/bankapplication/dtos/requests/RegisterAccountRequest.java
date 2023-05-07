package com.example.bankapplication.dtos.requests;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterAccountRequest {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private int pin;
}
