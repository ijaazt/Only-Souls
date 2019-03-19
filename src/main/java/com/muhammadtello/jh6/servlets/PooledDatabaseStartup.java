package com.muhammadtello.jh6.servlets;

import com.muhammadtello.jh6.connection_pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static com.muhammadtello.jh6.connection_pool.BasicConnectionPool.*;
import static com.muhammadtello.jh6.info.DatabaseInfo.*;
import static com.muhammadtello.jh6.info.ServletInfo.CONNECTION_POOL;

@WebListener
public class PooledDatabaseStartup implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName(DRIVER_CLASS_NAME.info()).newInstance();
            ConnectionPool connectionPool = createPool(URL.info(), USERNAME.info(), PASSWORD.info());
            sce.getServletContext().setAttribute(CONNECTION_POOL.info(), connectionPool);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool pool = (ConnectionPool) sce.getServletContext().getAttribute(CONNECTION_POOL.info());
        if(pool != null) {
            pool.closeAllConnections();
        }
    }
}
