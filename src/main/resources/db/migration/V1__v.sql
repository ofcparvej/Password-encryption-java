create TABLE booking
(
    id             BIGINT       NOT NULL,
    created_at     datetime     NOT NULL,
    updated_at     datetime     NOT NULL,
    booking_status VARCHAR(255) NULL,
    start_time     datetime     NULL,
    end_time       datetime     NULL,
    total_distance BIGINT       NULL,
    driver_id      BIGINT       NULL,
    passenger_id   BIGINT       NULL,
    CONSTRAINT pk_booking PRIMARY KEY (id)
);

create TABLE driver
(
    id             BIGINT       NOT NULL,
    created_at     datetime     NOT NULL,
    updated_at     datetime     NOT NULL,
    name           VARCHAR(255) NULL,
    license_number VARCHAR(255) NOT NULL,
    CONSTRAINT pk_driver PRIMARY KEY (id)
);

create TABLE passenger
(
    id           BIGINT       NOT NULL,
    created_at   datetime     NOT NULL,
    updated_at   datetime     NOT NULL,
    name         VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    CONSTRAINT pk_passenger PRIMARY KEY (id)
);

alter table driver
    add CONSTRAINT uc_driver_licensenumber UNIQUE (license_number);

alter table booking
    add CONSTRAINT FK_BOOKING_ON_DRIVER FOREIGN KEY (driver_id) REFERENCES driver (id);

alter table booking
    add CONSTRAINT FK_BOOKING_ON_PASSENGER FOREIGN KEY (passenger_id) REFERENCES passenger (id);