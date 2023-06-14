package ua.lab1.web.validators;

import ua.lab1.web.exceptions.CourseValidatorException;

import java.io.IOException;

public class CourseValidator {
    public Integer getValidatedCourseId(String courseIdStr) throws CourseValidatorException {
        if (courseIdStr == null) {
            throw new CourseValidatorException("No parameter courseId");
        }
        Integer courseId;
        try {
            courseId = Integer.parseInt(courseIdStr);
        } catch (NumberFormatException e) {
            throw new CourseValidatorException("Invalid parameter courseId");
        }
        return courseId;
    }
    public void validateName(String name) throws CourseValidatorException {
        if (name == null) {
            throw new CourseValidatorException("No parameter name");
        }
    }

    public Integer getValidatedMaxGrade(String maxGradeStr) {
        if (maxGradeStr == null) {
            throw new CourseValidatorException("No parameter maxGradeStr");
        }
        Integer maxGrade;
        try {
            maxGrade = Integer.parseInt(maxGradeStr);
        } catch (NumberFormatException e) {
            throw new CourseValidatorException("Invalid parameter maxGradeStr");
        }
        return maxGrade;
    }
}
