package ua.lab1.web.exceptions;

public class KeycloakSecurityServiceException extends RuntimeException {

    public KeycloakSecurityServiceException(Throwable exception){
        super(exception);
    }
    public KeycloakSecurityServiceException(String message){
        super(message);
    }
}
