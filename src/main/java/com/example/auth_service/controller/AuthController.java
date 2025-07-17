package com.example.auth_service.controller;


import com.example.auth_service.dto.AuthrequestDto;
import com.example.auth_service.dto.PassengerDto;
import com.example.auth_service.dto.PassengerSignupRequestDto;
import com.example.auth_service.services.AuthService;
import com.example.auth_service.services.JwtService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private AuthService authService;
    private  JwtService jwtService;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager , JwtService jwtService){
        this.authService=authService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto>signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto ){
        PassengerDto response = authService.signupPassenger(passengerSignupRequestDto);
        System.out.println("**********************************Controller******************************** ....");
        return new  ResponseEntity<>(response ,HttpStatus.ACCEPTED);
    }

    @PostMapping("/signin/passenger")
    public ResponseEntity<?>signIn(@RequestBody AuthrequestDto authrequestDto){
        System.out.println("Req---->" + authrequestDto.getEmail() + "   " + authrequestDto.getPassword());
//        PassengerDto response = authService.signupPassenger(passengerSignupRequestDto);
        System.out.println("**********************************SignIn Controller******************************** ....");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authrequestDto.getEmail() , authrequestDto.getPassword()));

        Map<String , Object> payload = new HashMap<>();
        payload.put("email",authrequestDto.getEmail());
//        String jwtToken = jwtService.createToken(payload , authentication.getPrincipal().toString() );
        String jwtToken = jwtService.createToken(authrequestDto.getEmail());

        if(authentication.isAuthenticated()){
            return new ResponseEntity<>(jwtToken , HttpStatus.OK);
        }
        return new  ResponseEntity<>(" Auth Not successful ", HttpStatus.OK);
    }
}
