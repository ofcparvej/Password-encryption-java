package com.example.auth_service.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthrequestDto {
    private String email;
    private String password;


}
