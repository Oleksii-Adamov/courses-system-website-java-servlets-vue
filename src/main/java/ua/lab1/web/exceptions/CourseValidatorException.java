package ua.lab1.web.exceptions;

public class CourseValidatorException extends RuntimeException {
    public CourseValidatorException(Throwable exception){
        super(exception);
    }
    public CourseValidatorException(String message){
        super(message);
    }
}
