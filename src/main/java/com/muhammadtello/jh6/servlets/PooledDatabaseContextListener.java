package com.muhammadtello.jh6.servlets;

import com.muhammadtello.jh6.database.ConnectionPool;
import com.mysql.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static com.muhammadtello.jh6.database.BasicConnectionPool.*;

@WebListener
public class PooledDatabaseContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/root";
            String username = "root";
            String password = "En7j6pur8v";
            ConnectionPool connectionPool = createPool(url, username, password);
            sce.getServletContext().setAttribute("connectionPool", connectionPool);
            sce.getServletContext().setAttribute("testAttribute", "Testing!!");
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
