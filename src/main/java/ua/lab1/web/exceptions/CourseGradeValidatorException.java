package ua.lab1.web.exceptions;

public class CourseGradeValidatorException extends RuntimeException {
    public CourseGradeValidatorException(Throwable exception){
        super(exception);
    }
    public CourseGradeValidatorException(String message){
        super(message);
    }
}
