package com.example.resourcium.controller;

import com.example.resourcium.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user input from the login form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (authenticateUser(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("loginSuccess", "Logged in successfully");

            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            // Authentication failed, display an error message
            HttpSession session = request.getSession();
            session.setAttribute("loginFailed", "Invalid email or password");
            request.getRequestDispatcher("/WEB-INF/Auth/login.jsp").forward(request, response);
        }
    }


    private boolean authenticateUser(String email, String password) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            // Query the database to find a user with the given email
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);

            User user = null;
            try {
                user = query.getSingleResult();
            } catch (Exception e) {
                // User not found
                return false;
            }

            // Compare the db password with given one
            return BCrypt.checkpw(password, user.getPassword());
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }
}
