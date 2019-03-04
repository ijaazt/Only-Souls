package com.muhammadtello.jh6.database;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection() throws TooManyConnectionsException;
    boolean releaseConnection(Connection connection);
    String getUrl();
    String getUser();
    String getPassword();
    int getMaxConnection();
}
