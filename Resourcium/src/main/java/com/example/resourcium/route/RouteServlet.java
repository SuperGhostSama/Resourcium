package com.example.resourcium.route;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RouteServlet", value = {"/dashboard", "/profile"})
public class RouteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        System.out.println(path);

        switch (path) {
            case "/dashboard":
                RequestDispatcher dashboardDispatcher = request.getRequestDispatcher("/WEB-INF/Dashboard/dashboard.jsp");
                dashboardDispatcher.forward(request, response);
                break;
            case "/profile":
                RequestDispatcher profileDispatcher = request.getRequestDispatcher("/WEB-INF/Dashboard/profile.jsp");
                profileDispatcher.forward(request, response);
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
