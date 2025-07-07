package com.example.auth_service.dto;


import com.example.auth_service.model.Passenger;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
    private String id;
    private String name;
    private String email;
    private String password;  //encrypted
    private String phoneNumber;
    private Date createdAt;
    private Date updatedAt;

    public static PassengerDto from(Passenger p){

        return PassengerDto.builder()
                .id(p.getId().toString())
                .email(p.getEmail())
                .name(p.getName())
                .createdAt(p.getCreatedAt())
                .updatedAt(p.getUpdatedAt())
                .phoneNumber(p.getPhoneNumber())
                .password(p.getPassword())
                .build();
    }
}
