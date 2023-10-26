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

@WebServlet(name = "EquipmentServlet", value = "/EquipmentServlet")
public class EquipmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String equipmentName = request.getParameter("equipmentName");
        String equipmentType = request.getParameter("equipmentType");

        Equipement equipment = new Equipement();
        equipment.setName(equipmentName);
        equipment.setType(equipmentType);
        equipment.setAvailability(true);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(equipment);

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

        // Redirect to the equipment page or any other page as needed
        response.sendRedirect(request.getContextPath() + "/reservations");
    }

}
