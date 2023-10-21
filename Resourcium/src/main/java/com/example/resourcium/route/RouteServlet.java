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
                RequestDispatcher dashboardDispatcher = request.getRequestDispatcher("/WEB-INF/Dashboard/dashboard.jsp");
                dashboardDispatcher.forward(request, response);
                break;
            case "/hhh":

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
