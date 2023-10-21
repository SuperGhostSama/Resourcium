package com.example.resourcium.controller;

import com.example.resourcium.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Dashboard/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the updated user information from the form
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");

        // Retrieve the user's email from the session
        String userEmailAddress = (String) request.getSession().getAttribute("email");

        // Check if the user is authenticated (exists in the session)
        if (userEmailAddress != null) {

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                // Retrieve the user entity by email
                User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                        .setParameter("email", userEmailAddress)
                        .getSingleResult();

                // Update the user's profile
                user.setFullName(fullName);
                user.setEmail(email);

                // Commit the transaction to save the changes
                transaction.commit();

                // Update the session attributes with the new values
                HttpSession session = request.getSession();
                session.setAttribute("fullName", fullName);
                session.setAttribute("email", email);
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                entityManager.close();
                entityManagerFactory.close();
            }

            // Redirect to the user's profile page
            response.sendRedirect(request.getContextPath() + "/profile");
        } else {
            // User is not authenticated redirect to a login page
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }


}
