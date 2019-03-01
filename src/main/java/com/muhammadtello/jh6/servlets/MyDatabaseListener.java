package com.muhammadtello.jh6.servlets;

import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

class MyDatabaseListener implements ServletContextListener {
    private DataSource dataSource = new DataSource();
    private Connection connection = getConnection();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("connectionJDBC", connection);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mitello");
        dataSource.setUsername("root");
        dataSource.setPassword("En7j6pur8v");

        try {
            return dataSource.getConnection();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
