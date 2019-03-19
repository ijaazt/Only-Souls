package com.muhammadtello.jh6.servlets;

import com.muhammadtello.jh6.beans.Person;
import com.muhammadtello.jh6.connection_pool.ConnectionPool;
import com.muhammadtello.jh6.database.PersonDAO;
import com.muhammadtello.jh6.exceptions.TooManyConnectionsException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import static com.muhammadtello.jh6.info.ServletInfo.*;

@WebServlet(name = "PersonCollection", urlPatterns = "/personCollection")
public class PersonCollection extends HttpServlet {
    private PersonDAO personDAO;
    private Connection connection;
    private ConnectionPool connectionPool;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connectionPool = (ConnectionPool) config.getServletContext().getAttribute(CONNECTION_POOL.info());
            try {
                connection = connectionPool.getConnection();
                personDAO = new PersonDAO(connection);
            } catch (TooManyConnectionsException e) {
                e.printStackTrace();
                connectionPool.increaseMaxConnection(10);
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSP_ADDRESS.info());
        try {
            ModelParameters modelParameters = new ModelParameters(request);
            Person person = modelParameters.getPerson();
            switch (modelParameters.getMethod()) {
                case DELETE: personDAO.deleteRow(person.getId());
                break;
                case PUT: personDAO.editRow(person.getId(), person);
                break;
                case POST: personDAO.createRow(person);
                break;
            }
            request.setAttribute(PERSON_LIST.info(), personDAO.getRows());
            System.out.println(personDAO.getRows());
        } catch (Exception e) {
            e.printStackTrace();
        }
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSP_ADDRESS.info());
        try {
            request.setAttribute(PERSON_LIST.info(), personDAO.getRows());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        requestDispatcher.forward(request, response);
    }


    @Override
    public void destroy() {
        connectionPool.releaseConnection(connection);
        getServletContext().setAttribute(CONNECTION_POOL.info(), connectionPool);
    }
}
