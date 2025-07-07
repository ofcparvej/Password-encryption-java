alter table course_students
    drop FOREIGN KEY FK532dg5ikp3dvbrbiiqefdoe6m;

alter table course_students
    drop FOREIGN KEY FK61ry13vjip2yrapfiag3mt9pq;

alter table course_students
    drop FOREIGN KEY FKgut5xj4l8sk6hg3l0t2su2pnc;

alter table booking
    add CONSTRAINT FK_BOOKING_ON_DRIVER FOREIGN KEY (driver_id) REFERENCES driver (id);

alter table booking
    add CONSTRAINT FK_BOOKING_ON_PASSENGER FOREIGN KEY (passenger_id) REFERENCES passenger (id);

drop table bookingreview_seq;

drop table course;

drop table course_students;

drop table hibernate_sequences;

drop table review_seq;

drop table student;

alter table booking_review
    drop COLUMN rating;

alter table booking_review
    modify booking_id BIGINT not NULL;

alter table booking
    drop COLUMN booking_status;

alter table booking
    add booking_status VARCHAR(255) NULL;