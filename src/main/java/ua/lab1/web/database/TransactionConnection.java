package ua.lab1.web.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionConnection {
    private final Connection con;
    private final boolean transactionFlag;

    public TransactionConnection(Connection con, boolean flag){

        this.con = con;
        this.transactionFlag = flag;
    }

    public PreparedStatement preparedStatement(String sql) throws SQLException {
        return con.prepareStatement(sql);
    }

    Connection getConnection() {
        return con;
    }

    public void commit() throws SQLException {
        if(!transactionFlag) {
            con.commit();
        }
    }

    public void rollBack() throws SQLException {
        if(!transactionFlag) {
            con.rollback();
        }
    }

    public void close() throws SQLException {
        if(!transactionFlag){
            con.close();
        }
    }
}
