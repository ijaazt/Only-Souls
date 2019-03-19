package com.muhammadtello.jh6.connection_pool;

import com.muhammadtello.jh6.exceptions.TooManyConnectionsException;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection() throws TooManyConnectionsException;
    boolean releaseConnection(Connection connection);
    String getUrl();
    String getUser();
    String getPassword();
    int getMaxConnection();
    void increaseMaxConnection(int max);
    void closeAllConnections();
}
