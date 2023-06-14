package ua.lab1.web.exceptions;

public class UserValidatorException extends RuntimeException {
    public UserValidatorException(Throwable exception){
        super(exception);
    }
    public UserValidatorException(String message){
        super(message);
    }
}
