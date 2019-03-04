package com.muhammadtello.jh6.servlets;

import com.muhammadtello.jh6.beans.Method;
import com.muhammadtello.jh6.beans.ModelParameters;
import com.muhammadtello.jh6.beans.Person;
import com.muhammadtello.jh6.database.ConnectionPool;
import com.muhammadtello.jh6.database.PersonDAO;
import com.muhammadtello.jh6.database.TooManyConnectionsException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "PersonCollectionServlet", urlPatterns = "/personCollection")
public class PersonCollectionServlet extends HttpServlet {
    final private String jspAddress = "/WEB-INF/PersonCollection.jsp";
    final private String connectionPoolName = "connectionPool";
    final private String personList = "personList";
    private PersonDAO personDAO;
    private Connection connection;
    private ConnectionPool connectionPool;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connectionPool = (ConnectionPool) config.getServletContext().getAttribute(connectionPoolName);
            try {
                connection = connectionPool.getConnection();
                personDAO = new PersonDAO(connection);
            } catch (TooManyConnectionsException | SQLException e) {
                e.printStackTrace();
            }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(jspAddress);
        try {
            ModelParameters modelParameters = new ModelParameters(request);
            System.out.println(modelParameters.getPerson());
            System.out.println(modelParameters.getMethod());
            Person person = modelParameters.getPerson();
            switch (modelParameters.getMethod()) {
                case DELETE: personDAO.deleteRow(person.getId());
                break;
                case PUT: personDAO.editRow(person.getId(), person);
                break;
                case POST: personDAO.createRow(person);
                break;
            }
            request.setAttribute(personList, personDAO.getRows());
            System.out.println(personDAO.getRows());
        } catch (Exception e) {
            e.printStackTrace();
        }
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(jspAddress);
        try {
            request.setAttribute(personList, personDAO.getRows());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        requestDispatcher.forward(request, response);
    }


    @Override
    public void destroy() {
        connectionPool.releaseConnection(connection);
        getServletContext().setAttribute(connectionPoolName, connectionPool);
    }
}
