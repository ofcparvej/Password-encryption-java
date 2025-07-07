package com.example.auth_service.model;

//package com.example.ProductService.Models;

public enum BookingStatus {

    SCHEDULED,

    CANCELLED,

    CAB_ARRIVED,

    ASSIGNING_DRIVER,

    IN_RIDE,

    COMPLETED

}

//to handle enum
//1) store it as string in db ...
//2) store it as number ...
//3) use string representation

