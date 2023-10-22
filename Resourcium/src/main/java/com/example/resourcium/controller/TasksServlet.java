package com.example.resourcium.controller;

import com.example.resourcium.model.Task;
import com.example.resourcium.model.User;
import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
@WebServlet(name = "TasksServlet", value = "/tasks")
public class TasksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            // Fetch all tasks
            List<Task> tasks = em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
            request.setAttribute("tasks", tasks);

            // Fetch all users
            List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
            request.setAttribute("users", users);
        } finally {
            em.close();
            emf.close();
        }

        request.getRequestDispatcher("/WEB-INF/Dashboard/tasks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from the request
        int assignedUserId = Integer.parseInt(request.getParameter("assignedTo"));
        String description = request.getParameter("description");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        // Parse the date strings into LocalDate objects
        LocalDate startDate = parseDate(startDateStr);
        LocalDate endDate = parseDate(endDateStr);

        // Create a Task entity and set its properties
        Task task = new Task();
        task.setDescription(description);

        // Convert LocalDate to Date
        task.setStartDate(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        task.setEndDate(Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        // Set the assigned user for the task
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            User assignedUser = entityManager.find(User.class, assignedUserId);
            task.setUser(assignedUser);

            entityManager.persist(task);
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

        // Redirect to the tasks page or any other page as needed
        response.sendRedirect(request.getContextPath() + "/tasks");
    }

    private LocalDate parseDate(String dateString) {
        if (dateString != null && !dateString.isEmpty()) {
            return LocalDate.parse(dateString);
        }
        return null;
    }



    private User getUserByFullName(String fullName) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            // Query the database to find the user with the given full name
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.fullName = :fullName", User.class);
            query.setParameter("fullName", fullName);

            // Execute the query and get the user
            User user = query.getSingleResult();
            return user;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }



    private void saveTaskToDatabase(Task task) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Persist the task entity to the database
            entityManager.persist(task);

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
    }


}
