package com.example.auth_service.services;

import com.example.auth_service.helper.AuthPassengerDetails;
import com.example.auth_service.model.Passenger;
import com.example.auth_service.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsserviceImpl implements UserDetailsService {


    @Autowired
    private  PassengerRepository passengerRepository;

//    public UserDetailsserviceImpl(PassengerRepository passengerRepository){
//        this.passengerRepository = passengerRepository;
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        Passenger passenger
        Optional<Passenger> passesnger = passengerRepository.findPassengerByEmail(username); //email is unique identifier ...

        if(passesnger.isPresent()){
            return new AuthPassengerDetails((passesnger.get()));
        }else{
            throw new UsernameNotFoundException("Cannot find User");
        }


//        return null;
    }
}

//loads user in form of userDetail Object for auth ..
