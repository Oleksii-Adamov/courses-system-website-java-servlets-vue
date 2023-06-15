package ua.lab1.web.database;

import ua.lab1.web.dao.DAOFactory;
import ua.lab1.web.exceptions.TransactionException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactory {
    private TransactionFactory() {}
    private static final TransactionFactory factory = new TransactionFactory();

    private final ThreadLocal<TransactionConnection> threadLocalTransactionConnection = new ThreadLocal<>();
    private final ConnectionFactory connectionFactory = ConnectionFactory.instance();

    public static TransactionFactory getInstance(){
        return factory;
    }

    public void beginTransaction() throws SQLException {
        if(threadLocalTransactionConnection.get() == null){
            Connection con = connectionFactory.getConnection();
            con.setAutoCommit(false);
            TransactionConnection transactionConnection = new TransactionConnection(con,true);
            threadLocalTransactionConnection.set(transactionConnection);
        }
    }

    public TransactionConnection getConnection() throws SQLException {
        if(threadLocalTransactionConnection.get()!=null){
            return threadLocalTransactionConnection.get();
        }
        Connection con = connectionFactory.getConnection();
        con.setAutoCommit(false);
        return new TransactionConnection(con,false);
    }

    public void endTransaction() throws SQLException, TransactionException {
        if(threadLocalTransactionConnection.get() == null){
            throw new TransactionException("transaction cannot be finished");
        }
        TransactionConnection transactionConnection = threadLocalTransactionConnection.get();
        threadLocalTransactionConnection.set(null);
        Connection con = transactionConnection.getConnection();
        try {
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw new TransactionException(e);
        }finally {
            connectionFactory.releaseConnection(con);
        }
    }

    public void rollbackTransaction() throws SQLException {
        if(threadLocalTransactionConnection.get() == null){
            throw new TransactionException("transaction cannot be finished");
        }
        TransactionConnection transactionConnection = threadLocalTransactionConnection.get();
        threadLocalTransactionConnection.set(null);
        Connection con = transactionConnection.getConnection();
        try {
            con.rollback();
        }
        catch (SQLException e) {
            throw new TransactionException(e);
        } finally {
            connectionFactory.releaseConnection(con);
        }
    }
}
