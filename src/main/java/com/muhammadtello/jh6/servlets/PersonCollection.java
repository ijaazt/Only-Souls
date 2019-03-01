package com.muhammadtello.jh6.servlets;

import com.muhammadtello.jh6.sql.PersonDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "com.muhammadtello.jh6.servlets.PersonCollection", urlPatterns = "/personCollection")
public class PersonCollection extends javax.servlet.http.HttpServlet {
    @Override
    public void init() {
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/com.muhammadtello.jh6.servlets.PersonCollection.jsp");
        PersonDAO dao = new PersonDAO((Connection) request.getAttribute("connectionJDBC"));
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse resp) {
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
    }
}
