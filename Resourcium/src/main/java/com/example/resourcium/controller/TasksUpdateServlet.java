package com.example.resourcium.controller;

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

@WebServlet(name = "TasksUpdateServlet", value = "/TasksUpdateServlet")
public class TasksUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from the request
        int taskId = Integer.parseInt(request.getParameter("taskIdUpdate"));  // Get the task ID
        int assignedUserIdUpdate = Integer.parseInt(request.getParameter("assignedToUpdate"));
        String descriptionUpdate = request.getParameter("descriptionUpdate");
        String startDateStrUpdate = request.getParameter("startDateUpdate");
        String endDateStrUpdate = request.getParameter("endDateUpdate");

        System.out.println(taskId);
        System.out.println(assignedUserIdUpdate);
        System.out.println(descriptionUpdate);
        System.out.println(startDateStrUpdate);
        System.out.println(endDateStrUpdate);

        // Parse the date strings into LocalDate objects
        LocalDate startDate = parseDate(startDateStrUpdate);
        LocalDate endDate = parseDate(endDateStrUpdate);

        // Retrieve the existing task from the database
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Task task = entityManager.find(Task.class, taskId);  // Find the existing task by ID

            if (task != null) {
                // Update the task properties
                task.setDescription(descriptionUpdate);
                task.setStartDate(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                task.setEndDate(Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

                // Set the assigned user for the task
                User assignedUser = entityManager.find(User.class, assignedUserIdUpdate);
                task.setUser(assignedUser);

                // Commit the transaction to save the changes
                entityManager.merge(task);
            }

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

        response.sendRedirect(request.getContextPath() + "/tasks");

    }

    private LocalDate parseDate(String dateString) {
        if (dateString != null && !dateString.isEmpty()) {
            return LocalDate.parse(dateString);
        }
        return null;
    }

}
