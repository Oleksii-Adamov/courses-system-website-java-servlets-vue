package ua.lab1.web.database;

import ua.lab1.web.dao.DAOFactory;
import ua.lab1.web.exceptions.ConnectionPoolException;
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
            TransactionConnection transactionConnection = new TransactionConnection(con);
            threadLocalTransactionConnection.set(transactionConnection);
        }
    }

    public TransactionConnection getConnection() throws SQLException {
        if(threadLocalTransactionConnection.get()!=null){
            return threadLocalTransactionConnection.get();
        }
        Connection con = connectionFactory.getConnection();
        con.setAutoCommit(false);
        return new TransactionConnection(con);
    }

    public void endTransaction() throws SQLException, TransactionException {
        if(threadLocalTransactionConnection.get() == null){
            throw new TransactionException("transaction cannot be finished");
        }
        TransactionConnection transactionConnection = threadLocalTransactionConnection.get();
        Connection connection = transactionConnection.getConnection();
        threadLocalTransactionConnection.set(null);
        try {
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new TransactionException(e);
        }finally {
            try {
                connectionFactory.releaseConnection(connection);
            } catch (SQLException | ConnectionPoolException e) {
                throw new TransactionException(e);
            }
        }
    }

    public void rollbackTransaction() throws SQLException, ConnectionPoolException {
        if(threadLocalTransactionConnection.get() == null){
            throw new TransactionException("transaction cannot be finished");
        }
        TransactionConnection transactionConnection = threadLocalTransactionConnection.get();
        threadLocalTransactionConnection.set(null);
        Connection connection = transactionConnection.getConnection();
        try {
            connection.rollback();
        }
        catch (SQLException e) {
            throw new TransactionException(e);
        } finally {
            connectionFactory.releaseConnection(connection);
        }
    }
}
