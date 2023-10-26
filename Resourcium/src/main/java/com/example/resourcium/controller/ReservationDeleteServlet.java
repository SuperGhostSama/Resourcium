package com.example.resourcium.controller;

import com.example.resourcium.model.Reservation;
import com.example.resourcium.model.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ReservationDeleteServlet", value = "/ReservationDeleteServlet")
public class ReservationDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the reservation ID from the request
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            // Find the reservation to delete
            Reservation reservationToDelete = em.find(Reservation.class, reservationId);

            // Check if the reservation exists
            if (reservationToDelete != null) {
                EntityTransaction transaction = em.getTransaction();

                try {
                    transaction.begin();
                    em.remove(reservationToDelete); // Delete the reservation
                    transaction.commit();
                } catch (Exception e) {
                    if (transaction != null && transaction.isActive()) {
                        transaction.rollback();
                    }
                    e.printStackTrace();
                }
            }
        } finally {
            em.close();
            emf.close();
        }

        response.sendRedirect(request.getContextPath() + "/reservations");
    }


}
