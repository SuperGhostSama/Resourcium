package com.example.resourcium.model;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

}
