package ua.lab1.web.exceptions;

public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException(Throwable exception){
        super(exception);
    }
    public ConnectionPoolException(String message){
        super(message);
    }
}
