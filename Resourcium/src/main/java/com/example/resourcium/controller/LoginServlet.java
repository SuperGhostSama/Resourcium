package com.example.resourcium.controller;

import com.example.resourcium.model.User;
import jakarta.persistence.*;
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

        // Authenticate the user and retrieve user information from the database
        User user = authenticateUser(email, password);

        if (user != null) {
            // User logged in successfully
            HttpSession session = request.getSession();
            session.setAttribute("loginSuccess", "Logged in successfully");
            session.setAttribute("fullName", user.getFullName()); // Set the user's full name
            session.setAttribute("email", email);

            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            // Authentication failed, display an error message
            HttpSession session = request.getSession();
            session.setAttribute("loginFailed", "Invalid email or password");
            request.getRequestDispatcher("/WEB-INF/Auth/login.jsp").forward(request, response);
        }
    }



    private User authenticateUser(String email, String password) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            // Query the database to find a user with the given email
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);

            try {
                User user = query.getSingleResult();

                // Compare the hashed password
                if (BCrypt.checkpw(password, user.getPassword())) {
                    return user; // Authentication successful
                }
            } catch (NoResultException e) {
                // User not found
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return null; // Authentication failed
    }
}
