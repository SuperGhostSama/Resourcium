package com.example.resourcium.controller;

import com.example.resourcium.controller.RegisterServlet;
import com.example.resourcium.model.Role;
import com.example.resourcium.model.User;
import jakarta.servlet.ServletException;
import org.mindrot.jbcrypt.BCrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class RegisterServletTest {

    private RegisterServlet registerServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        registerServlet = new RegisterServlet();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);

        entityManagerFactory = Mockito.mock(EntityManagerFactory.class);
        entityManager = Mockito.mock(EntityManager.class);

        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
    }

    @Test
    void testDoGet() throws ServletException, IOException {
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/WEB-INF/Auth/register.jsp")).thenReturn(dispatcher);

        registerServlet.doGet(request, response);

        Mockito.verify(dispatcher).forward(request, response);
    }


}
