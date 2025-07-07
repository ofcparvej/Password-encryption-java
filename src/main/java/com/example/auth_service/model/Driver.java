package com.example.auth_service.model;

//package com.example.ProductService.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor


//@JsonIgnoreProperties( {"hibernateLazyInitializer" , "handler" , "bookings"}) //another way to handle recursive response . . .
public class Driver extends BaseModel{

    private String name;

    @Column(nullable = false , unique = true)
    private String licenseNumber;

    //1:n  Drive:Bookings

    @OneToMany (mappedBy = "driver" , fetch = FetchType.LAZY) // in other table also ,make a change .. i.e in booking
    // now by specifying this --> No extra driver_review table is created

    // FETCHType.EAGER=>( queryy )==>  select d1_0.id,d1_0.created_at,d1_0.license_number,d1_0.name,d1_0.updated_at,b1_0.driver_id,b1_0.id,b1_0.booking_status,b1_0.created_at,b1_0.end_time,p1_0.id,p1_0.created_at,p1_0.name,p1_0.updated_at,r1_0.id,case when r1_1.driver_review_id is not null then 1 when r1_0.id is not null then 0 end,r1_0.content,r1_0.created_at,r1_0.rating,r1_0.updated_at,r1_1.passenger_rating,r1_1.passenger_review_comment,b1_0.start_time,b1_0.total_distance,b1_0.updated_at from driver d1_0 left join booking b1_0 on d1_0.id=b1_0.driver_id left join passenger p1_0 on p1_0.id=b1_0.passenger_id left join booking_review r1_0 on r1_0.id=b1_0.review_id left join passenger_review r1_1 on r1_0.id=r1_1.driver_review_id where d1_0.id=?
    // see it fetches all the stuff ...all the joins and stuff : )\


    // FetchType.LAZY ==> (simple query) ==>>
    //select d1_0.id,d1_0.created_at,d1_0.license_number,d1_0.name,d1_0.updated_at from driver d1_0 where d1_0.id=?


    // OneToOne -> default-> EAGER FETCH MODE
    // OneToMany -> Lazy
    // ManyToOne -> EAGER
    // ManyToMany -> Lazy  (last ,me many -> lazy)

    // immedietly need -> eager
    // e.g qoura -> ans needed eagerly -> comments lazily


    //N + 1
    @Fetch(value = FetchMode.SUBSELECT)   // to avoid N+1 queries =>
    private Set<Booking> bookings = new HashSet<>();


    @Override
    public String toString(){
        return "driver->" + this.name + "  " + this.licenseNumber;
    }
}

// no ref from driver to booking
// ref from booking to driver is there => Many side has foregn key ...
//One extra table is added driver_bookings => flaw of spring Data JPA => Mistake
