package com.example.resourcium.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.when;

public class LoginServletTest {

    private LoginServlet loginServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    void setUp() {
        loginServlet = new LoginServlet();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testDoGetAuthenticatedUser() throws ServletException, IOException {
        when(session.getAttribute("fullName")).thenReturn("John Doe");
        when(session.getAttribute("email")).thenReturn("john@example.com");
        when(session.getAttribute("role")).thenReturn("Administrator");

        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/WEB-INF/Dashboard/dashboard.jsp")).thenReturn(dispatcher);

        loginServlet.doGet(request, response);

        Mockito.verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoGetUnauthenticatedUser() throws ServletException, IOException {
        when(session.getAttribute("fullName")).thenReturn(null);
        when(session.getAttribute("email")).thenReturn(null);
        when(session.getAttribute("role")).thenReturn(null);

        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/WEB-INF/Auth/login.jsp")).thenReturn(dispatcher);

        loginServlet.doGet(request, response);

        Mockito.verify(dispatcher).forward(request, response);
    }
}
