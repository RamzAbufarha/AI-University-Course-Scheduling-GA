package model;

public class StudentGroup {

    private String id;
    private String name;

    public StudentGroup(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}