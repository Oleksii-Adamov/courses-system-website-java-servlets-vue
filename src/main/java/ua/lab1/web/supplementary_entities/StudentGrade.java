package ua.lab1.web.supplementary_entities;

import ua.lab1.web.enitities.Entity;

public class StudentGrade implements Entity {
    private Integer grade;
    private String teacherResponse;
    private Integer maxGrade;

    public StudentGrade() {
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getTeacherResponse() {
        return teacherResponse;
    }

    public void setTeacherResponse(String teacherResponse) {
        this.teacherResponse = teacherResponse;
    }

    public Integer getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(Integer maxGrade) {
        this.maxGrade = maxGrade;
    }
}
