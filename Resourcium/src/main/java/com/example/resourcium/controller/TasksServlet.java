package com.example.resourcium.controller;

import com.example.resourcium.model.Task;
import com.example.resourcium.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

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

    }
}
