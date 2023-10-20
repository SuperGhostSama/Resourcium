package com.example.resourcium.controller;

import com.example.resourcium.model.Role;
import com.example.resourcium.model.User;
import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Auth/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user input from the registration form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Hash the password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Create an EntityManager and a Role
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Check if a user with the given email already exists
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);

        List<User> existingUsers = query.getResultList();

        if (!existingUsers.isEmpty()) {
            // User with the same email already exists, set an error message in the session
            HttpSession session = request.getSession();
            System.out.println("Email already exists");
            session.setAttribute("registrationError", "Email already exists. Please choose another.");
            response.sendRedirect(request.getContextPath() + "/register");
        } else {
            // No user with the same email, proceed with registration
            // Create a new user entity and set the user data
            User user = new User();
            user.setFullName(name);
            user.setEmail(email);
            user.setPassword(hashedPassword);

            // Retrieve the role with ID 2 from the database
            Role role = entityManager.find(Role.class, 2); // Assuming Role entity has an ID of 2

            // Set the role of the user
            user.setRole(role);

            // Save the user entity to the database
            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();
                entityManager.persist(user);
                transaction.commit();

                // Set a registration success message in the session
                HttpSession session = request.getSession();
                session.setAttribute("registrationSuccess", "Registration successful. You can now log in.");
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                entityManager.close();
                entityManagerFactory.close();
            }

            response.sendRedirect(request.getContextPath() + "/login");
        }
    }



}

