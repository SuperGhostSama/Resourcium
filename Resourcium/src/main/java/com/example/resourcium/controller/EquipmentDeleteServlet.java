package com.example.resourcium.controller;

import com.example.resourcium.model.Equipement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "EquipmentDeleteServlet", value = "/EquipmentDeleteServlet")
public class EquipmentDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the equipment ID from the request
        int equipmentId = Integer.parseInt(request.getParameter("equipmentId"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            // Find the equipment to delete
            Equipement equipmentToDelete = em.find(Equipement.class, equipmentId);

            // Check if the equipment exists
            if (equipmentToDelete != null) {
                EntityTransaction transaction = em.getTransaction();

                try {
                    transaction.begin();
                    em.remove(equipmentToDelete); // Delete the equipment
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

        // Redirect to the equipment page or any other page as needed
        response.sendRedirect(request.getContextPath() + "/reservations");
    }

}
