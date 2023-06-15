package ua.lab1.web.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionConnection {
    private final Connection con;

    public TransactionConnection(Connection con){
        this.con = con;
    }

    public PreparedStatement preparedStatement(String sql) throws SQLException {
        return con.prepareStatement(sql);
    }

    public PreparedStatement preparedStatement(String sql, int option) throws SQLException {
        return con.prepareStatement(sql, option);
    }

    Connection getConnection() {
        return con;
    }
}
