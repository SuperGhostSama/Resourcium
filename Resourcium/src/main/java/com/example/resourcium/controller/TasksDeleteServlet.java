package com.example.resourcium.controller;

import com.example.resourcium.model.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "TasksDeleteServlet", value = "/TasksDeleteServlet")
public class TasksDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hello from doDelete");
        // Retrieve the task ID from the request
        int taskId = Integer.parseInt(request.getParameter("taskId"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            // Find the task to delete
            Task taskToDelete = em.find(Task.class, taskId);

            // Check if the task exists
            if (taskToDelete != null) {
                EntityTransaction transaction = em.getTransaction();

                try {
                    transaction.begin();
                    em.remove(taskToDelete); // Delete the task
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

        response.sendRedirect(request.getContextPath() + "/tasks");
    }

}
