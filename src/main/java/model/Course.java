package model;

public class Course {

    private String id;
    private String name;
    private int students;
    private Instructor instructor;
    private String type;
    private String studentGroup;

    public Course(String id, String name, int students,
            Instructor instructor, String type) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.instructor = instructor;
        this.type = type;
    }

    public Course(String id, String name, int students,
            Instructor instructor, String type,
            String studentGroup) {

        this.id = id;
        this.name = name;
        this.students = students;
        this.instructor = instructor;
        this.type = type;
        this.studentGroup = studentGroup;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStudents() {
        return students;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public String getType() {
        return type;
    }

    public String getStudentGroup() {
        return studentGroup;
    }
}