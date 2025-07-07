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

create TABLE booking_review
(
    id         BIGINT       NOT NULL,
    created_at datetime     NOT NULL,
    updated_at datetime     NOT NULL,
    content    VARCHAR(255) NOT NULL,
    rating     DOUBLE       NULL,
    booking_id BIGINT       NOT NULL,
    CONSTRAINT pk_booking_review PRIMARY KEY (id)
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
    id         BIGINT       NOT NULL,
    created_at datetime     NOT NULL,
    updated_at datetime     NOT NULL,
    name       VARCHAR(255) NULL,
    CONSTRAINT pk_passenger PRIMARY KEY (id)
);

create TABLE passenger_review
(
    driver_review_id         BIGINT       NOT NULL,
    passenger_review_comment VARCHAR(255) NOT NULL,
    passenger_rating         VARCHAR(255) NOT NULL,
    CONSTRAINT pk_passengerreview PRIMARY KEY (driver_review_id)
);

alter table booking_review
    add CONSTRAINT uc_booking_review_booking UNIQUE (booking_id);

alter table driver
    add CONSTRAINT uc_driver_licensenumber UNIQUE (license_number);

alter table booking
    add CONSTRAINT FK_BOOKING_ON_DRIVER FOREIGN KEY (driver_id) REFERENCES driver (id);

alter table booking
    add CONSTRAINT FK_BOOKING_ON_PASSENGER FOREIGN KEY (passenger_id) REFERENCES passenger (id);

alter table booking_review
    add CONSTRAINT FK_BOOKING_REVIEW_ON_BOOKING FOREIGN KEY (booking_id) REFERENCES booking (id);

alter table passenger_review
    add CONSTRAINT FK_PASSENGERREVIEW_ON_DRIVER_REVIEW FOREIGN KEY (driver_review_id) REFERENCES booking_review (id);