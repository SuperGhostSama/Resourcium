package com.example.resourcium.controller;

import com.example.resourcium.model.Equipement;
import com.example.resourcium.model.Reservation;
import com.example.resourcium.model.Task;
import com.example.resourcium.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ReservationServlet", value = "/reservations")
public class ReservationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            // Fetch all reservations
            List<Reservation> reservations = em.createQuery("SELECT t FROM Reservation t", Reservation.class).getResultList();
            request.setAttribute("reservations", reservations);

            // Fetch all equipment
            List<Equipement> equipment = em.createQuery("SELECT u FROM Equipement u", Equipement.class).getResultList();
            request.setAttribute("equipment", equipment);

        } finally {
            em.close();
            emf.close();
        }
        request.getRequestDispatcher("/WEB-INF/Dashboard/reservation.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int reservationUserId =17;
        int reservationEquipementId =2 ;
        String reservationDateStr = "2023-10-24";
        String returnDateStr = "2023-10-27";

        Reservation reservation = new Reservation();


        // Parse the date strings into LocalDate objects
        LocalDate reservationDate = parseDate(reservationDateStr);
        LocalDate returnDate = parseDate(returnDateStr);

        // Convert LocalDate to Date
        reservation.setReservationDate(Date.from(reservationDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        reservation.setReturnDate(Date.from(returnDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            User assignedUser = entityManager.find(User.class, reservationUserId);
            reservation.setUser(assignedUser);
            Equipement equipement = entityManager.find(Equipement.class, reservationEquipementId);
            reservation.setEquipement(equipement);

            entityManager.persist(reservation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        System.out.println("Reservation saved successefully");
        System.out.println(reservationUserId);
        System.out.println(reservationEquipementId);
        System.out.println(reservationDateStr);
        System.out.println(returnDateStr);
    }

    private LocalDate parseDate(String dateString) {
        if (dateString != null && !dateString.isEmpty()) {
            return LocalDate.parse(dateString);
        }
        return null;
    }

}
