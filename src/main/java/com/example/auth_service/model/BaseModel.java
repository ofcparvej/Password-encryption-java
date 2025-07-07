package com.example.auth_service.model;

//package com.example.ProductService.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


// will have properties which all class will require ...
//abstraction bcx not reallife entity-> code level segregation olny



@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date updatedAt;

    //protected->all derived classes can access
}

//mySQL wiill give error -> it doesnt know about inheritance-> thus error
//springdata jpa also confused -> how to handle inheritance

//@MappSuperClass ==> No Error : )
//it say -> No table for parent class &  one table for each child class having its own attributes & par class attributes
// 99.9 % -> id , createdAt , updatedAt
