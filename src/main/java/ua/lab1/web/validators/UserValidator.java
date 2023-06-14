package ua.lab1.web.validators;

import ua.lab1.web.exceptions.UserValidatorException;

public class UserValidator {
    public void validateUserId(String userId) throws UserValidatorException {
        if (userId == null ) {
            throw new UserValidatorException("No parameter userId");
        }
        if (userId.length() != 36) {
            throw new UserValidatorException("Invalid parameter userId");
        }
    }
    public void validateFullName(String fullName) throws UserValidatorException {
        if (fullName == null) {
            throw new UserValidatorException("No parameter fullName");
        }
    }
}
