package com.example.auth_service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PassengerSignupRequestDto {
    private String email;
    private String password;
    private String phoneNumber;
    private String name;
}
