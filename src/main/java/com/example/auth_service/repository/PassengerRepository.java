package com.example.auth_service.repository;

import com.example.auth_service.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
//    @Override
    Optional<Passenger> findPassengerByEmail(String email);
}
