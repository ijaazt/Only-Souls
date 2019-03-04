package com.muhammadtello.jh6.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool {
    private String url;
    private String user;
    private String password;
    private List<Connection> unUsedConnections;
    private List<Connection> usedConnections;
    private static final int MAX_CONNECTIONS = 20;

    public static ConnectionPool createPool(
            String url, String user, String password
    ) throws SQLException {
        List<Connection> connectionList = new ArrayList<>();
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
             connectionList.add(createConnection(url, user, password));
        }
        return new BasicConnectionPool(url, user, password, connectionList);
    }

    static Connection createConnection(
            String url, String user, String password
    ) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private BasicConnectionPool(String url, String user, String password, List<Connection> connections) {
        this.url = url;
        this.user = user;
        this.password  = password;
        this.unUsedConnections = connections;
        this.usedConnections = new ArrayList<>();
    }

    @Override
    public Connection getConnection() throws TooManyConnectionsException {
        int connectionPointer = unUsedConnections.size() - 1;
        if(connectionPointer < 0) {
            throw new TooManyConnectionsException();
        }
        Connection currentConnection = unUsedConnections.remove(connectionPointer);
        usedConnections.add(currentConnection);
        return currentConnection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        boolean connectionIsBeingUsed = usedConnections.contains(connection);
        if(connectionIsBeingUsed) {
            int connectionPointer = usedConnections.indexOf(connection);
            Connection connectionToBeReleased = usedConnections.remove(connectionPointer);
            return unUsedConnections.add(connectionToBeReleased);
        } else {
            return false;
        }
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getMaxConnection() {
        return MAX_CONNECTIONS;
    }
}
