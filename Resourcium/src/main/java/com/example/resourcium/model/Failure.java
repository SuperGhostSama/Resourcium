package com.example.resourcium.model;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Failure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reportingDate;

    @ManyToOne
    @JoinColumn(name = "equipement_id", referencedColumnName = "id")
    private Equipement equipement;
}
