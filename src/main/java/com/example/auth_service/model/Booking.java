package com.example.auth_service.model;

//package com.example.ProductService.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity //vimp to create table ..of given model


public class Booking extends BaseModel{

    //every booking have review - > review belong to booking
//    @OneToOne (cascade = {CascadeType.PERSIST , CascadeType.REMOVE} , fetch = FetchType.LAZY) // to avoid conflict -> what can be stored first
//    private Review review;

    //it will alter table and add review in booking
    // also add foregn key automatically => review_id
    // sets one to one from java to mysql world
    // now id property of booking same as review_id
    //*****CASCADE TYPE*****

    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endTime;

    private Long totalDistance;

    @ManyToOne (fetch = FetchType.LAZY) // Booking:Driver
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)  // Booking:passenger
    private Passenger passenger;

    @Override
    public String toString(){
        return "booking->" + this.bookingStatus + "  " + this.startTime;
    }


}

