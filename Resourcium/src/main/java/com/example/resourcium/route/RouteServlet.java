package com.example.resourcium.route;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RouteServlet", value = {"/dashboard", "/hh"})
public class RouteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        System.out.println(path);

        switch (path) {
            case "/dashboard":
                HttpSession session = request.getSession();
                String fullName = (String) session.getAttribute("fullName");
                String email = (String) session.getAttribute("email");
                String role = (String) session.getAttribute("role");

                if (fullName != null && email != null && role != null) {
                    // User is authenticated, redirect to the dashboard
                    request.getRequestDispatcher("/WEB-INF/Dashboard/dashboard.jsp").forward(request, response);
                } else {
                    // User is not authenticated, redirect to the login page
                    request.getRequestDispatcher("/WEB-INF/Auth/login.jsp").forward(request, response);
                }
                break;
            case "/nopage":

                break;
            default:
                response.sendRedirect("/errorPage");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
