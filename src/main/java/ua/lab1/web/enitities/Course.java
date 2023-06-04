package ua.lab1.web.enitities;

public class Course implements Entity {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
