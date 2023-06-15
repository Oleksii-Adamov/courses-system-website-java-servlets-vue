package ua.lab1.web.database;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.lab1.web.exceptions.ConnectionPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private final String DbUrl = "jdbc:postgresql://localhost:5432/courses_db_keycloak";
    private final String username = "postgres";
    private final String password = "root";

    private BlockingQueue<Connection> connections;

    public ConnectionPool(int maxConnections) throws ConnectionPoolException {
        try {
            Class.forName("org.postgresql.Driver");

            connections = new ArrayBlockingQueue<>(maxConnections);

            for(int i = 0; i < maxConnections; ++i) {
                connections.put(DriverManager.getConnection(DbUrl, username, password));
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new ConnectionPoolException(e);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
            throw new ConnectionPoolException(e);
        }
    }

    public Connection getConnection() throws ConnectionPoolException {
        try {
            return connections.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConnectionPoolException(e);
        }
    }

    public void releaseConnection(Connection connection) throws SQLException, ConnectionPoolException {
        try {
            if (connection.isClosed()) {
                connections.put(DriverManager.getConnection(DbUrl, username, password));
            } else {
                connections.put(connection);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConnectionPoolException(e);
        }
    }
}
