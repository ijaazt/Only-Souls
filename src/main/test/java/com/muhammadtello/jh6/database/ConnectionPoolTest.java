package com.muhammadtello.jh6.database;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ConnectionPoolTest {
    private ConnectionPool connectionPool;

    @Before
    @Test
    public void setConnectionPool() throws SQLException {
        String database = "";
        String url = "jdbc:mysql://localhost:3306/"+database;
        String username = "root";
        String password = "En7j6pur8v";
        connectionPool = BasicConnectionPool.createPool(url, username, password);
        assertNotNull(connectionPool);
    }


    @Test(expected = SQLException.class)
    public void invalidConnectionPool() throws SQLException {
        String url = "";
        String username = "";
        String password  = "";
        BasicConnectionPool.createPool(url, username, password);
    }

    @Test
    public void gettingConnection() throws TooManyConnectionsException {
        assertNotNull(connectionPool.getConnection());
    }

    @Test
    public void releasingConnections() throws TooManyConnectionsException {
        List<Connection> connectionList = new ArrayList<>();
        for(int i = 0; i < connectionPool.getMaxConnection(); i++) {
            connectionList.add(connectionPool.getConnection());
        }
        assertTrue(connectionPool.releaseConnection(connectionList.get(0)));
        Connection myConnection = connectionPool.getConnection();
        assertTrue(connectionPool.releaseConnection(myConnection));
    }

    @Test
    public void releasingSeparateConnection() throws SQLException {
        Connection connection = BasicConnectionPool.createConnection(
                connectionPool.getUrl(),
                connectionPool.getUser(),
                connectionPool.getPassword()
        );
        assertFalse(connectionPool.releaseConnection(connection));
    }

    @Test(expected = TooManyConnectionsException.class)
    public void tooManyConnections() throws TooManyConnectionsException {
        int largeConnection = 20;
        for (int i = 0; i < largeConnection; i++) {
            connectionPool.getConnection();
        }
    }

}
