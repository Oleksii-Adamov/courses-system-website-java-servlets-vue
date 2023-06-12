package ua.lab1.web.enitities;

public class Course implements Entity {

    private Integer id;
    private String name;

    private String teacherId;
    private Integer maxGrade;


    public Course() {
    }

    public Course(int id, String name, String teacherId, int maxGrade) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
        this.maxGrade = maxGrade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(int maxGrade) {
        this.maxGrade = maxGrade;
    }
}
