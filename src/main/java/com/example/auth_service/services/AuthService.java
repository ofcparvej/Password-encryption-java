package com.example.auth_service.services;

import com.example.auth_service.dto.PassengerDto;
import com.example.auth_service.dto.PassengerSignupRequestDto;
import com.example.auth_service.model.Passenger;
import com.example.auth_service.repository.PassengerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PassengerRepository passengerRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(PassengerRepository passengerRepository , BCryptPasswordEncoder bCryptPasswordEncoder){  // coming from configuration
        this.passengerRepository = passengerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public PassengerDto signupPassenger(PassengerSignupRequestDto passengerSignupRequestDto){
        Passenger passenger =  Passenger.builder().email(passengerSignupRequestDto.getEmail()).name(passengerSignupRequestDto.getName()).password(bCryptPasswordEncoder.encode(passengerSignupRequestDto.getPassword())).phoneNumber(passengerSignupRequestDto.getPhoneNumber()).build(); //todo encrypt password

        Passenger newPassenger = passengerRepository.save(passenger);

        return PassengerDto.from(newPassenger);


    }



}
