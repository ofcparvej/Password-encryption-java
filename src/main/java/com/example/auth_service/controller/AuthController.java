package com.example.auth_service.controller;


import com.example.auth_service.dto.PassengerDto;
import com.example.auth_service.dto.PassengerSignupRequestDto;
import com.example.auth_service.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto>signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto ){
        PassengerDto response = authService.signupPassenger(passengerSignupRequestDto);
        System.out.println("**********************************Controller******************************** ....");
        return new  ResponseEntity<>(response ,HttpStatus.ACCEPTED);
    }

    @GetMapping("/signin/passenger")
    public ResponseEntity<?>signIn(){
//        PassengerDto response = authService.signupPassenger(passengerSignupRequestDto);
//        System.out.println("**********************************Controller******************************** ....");
        return new  ResponseEntity<>(" : ) ", HttpStatus.ACCEPTED);
    }
}
