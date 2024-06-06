package com.example.demo.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "reservations")
@Data
@ToString
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "room_id")
    private long roomId;
    @Column(name = "guest_id")
    private long guestId;
    @Column(name = "res_date")
    private Date reservationDate;

}

