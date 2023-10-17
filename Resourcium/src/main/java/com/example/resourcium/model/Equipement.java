package com.example.resourcium.model;
import jakarta.persistence.*;

@Entity
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String type;
    private boolean availability;
}
