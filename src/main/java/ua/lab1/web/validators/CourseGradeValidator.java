package ua.lab1.web.validators;


import ua.lab1.web.exceptions.CourseGradeValidatorException;

public class CourseGradeValidator {
    public Integer getValidatedGrade(String gradeStr) throws CourseGradeValidatorException {
        if (gradeStr == null) {
            throw new CourseGradeValidatorException("No parameter grade");
        }
        Integer grade;
        try {
            grade = Integer.parseInt(gradeStr);
        } catch (NumberFormatException e) {
            throw new CourseGradeValidatorException("Invalid parameter grade");
        }
        return grade;
    }
    public void validateTeacherResponse(String teacherResponse) throws CourseGradeValidatorException {
        if (teacherResponse == null) {
            throw new CourseGradeValidatorException("No parameter teacherResponse");
        }
    }
}
