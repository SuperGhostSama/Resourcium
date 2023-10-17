package com.example.resourcium.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class AuthController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action"); // Action parameter from the form

        if (action == null) {
            // Handle invalid action
            response.sendRedirect("/errorPage");
            return;
        }

        if (action.equals("login")) {
            // Handle login
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Authenticate the user, e.g., check credentials in a database
            if (authenticateUser(username, password)) {
                // Successful login
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("/dashboard");
            } else {
                // Failed login
                response.sendRedirect("/login?error=1");
            }
        } else if (action.equals("register")) {
            // Handle registration
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Register the user, e.g., add user data to a database
            if (registerUser(username, password)) {
                // Successful registration
                response.sendRedirect("/login");
            } else {
                // Failed registration
                response.sendRedirect("/register?error=1");
            }
        } else if (action.equals("logout")) {
            // Handle logout
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("/login");
        } else {
            // Handle unknown action
            response.sendRedirect("/errorPage");
        }
    }

    private boolean authenticateUser(String username, String password) {
        // Implement user authentication logic, e.g., check user credentials in a database
        return true; // Replace with your authentication logic
    }

    private boolean registerUser(String username, String password) {
        // Implement user registration logic, e.g., add user data to a database
        return true; // Replace with your registration logic
    }
}
