package com.example.resourcium;

import com.example.resourcium.model.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.ZoneId;
import java.util.Date;


public class test {
    public static void main(String[] args) {
        //Calling the persistance unit default to generate db with Entities

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        entityManager.close();
        entityManagerFactory.close();


    }
}
