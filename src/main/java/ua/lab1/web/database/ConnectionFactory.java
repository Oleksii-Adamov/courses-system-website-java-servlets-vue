package ua.lab1.web.database;

import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final ConnectionFactory factory = new ConnectionFactory();
    //Since 42.0.0, instead of this class you should use a fully featured connection pool like HikariCP, vibur-dbcp, commons-dbcp, c3p0, etc.
    private final Jdbc3PoolingDataSource source = new Jdbc3PoolingDataSource();
    private static final int MAX_CONNECTIONS = 10;
    private ConnectionFactory() {
        source.setDataSourceName("coursesdb data source");
        source.setServerName("localhost");
        source.setDatabaseName("courses_db_keycloak");
        source.setUser("postgres");
        source.setPassword("root");
        source.setMaxConnections(MAX_CONNECTIONS);
    }

    public static ConnectionFactory instance(){
        return factory;
    }

    public synchronized Connection getConnection() throws SQLException {
        return source.getConnection();
    }

}