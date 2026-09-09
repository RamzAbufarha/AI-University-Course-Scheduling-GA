package data;

import model.Course;
import model.Instructor;
import model.Room;

public class DataSet {

    public static Instructor[] createInstructors() {

        return new Instructor[] {
                new Instructor("I01", "Dr. Ahmad"),
                new Instructor("I02", "Dr. Sara"),
                new Instructor("I03", "Dr. Omar"),
                new Instructor("I04", "Dr. Lina"),
                new Instructor("I05", "Dr. Khaled"),
                new Instructor("I06", "Dr. Noor"),
                new Instructor("I07", "Dr. Rami"),
                new Instructor("I08", "Dr. Huda")
        };
    }

    public static Course[] createCourses() {

        Instructor[] instructors = createInstructors();

        return new Course[] {
                new Course("C01", "AI", 35, instructors[0], "Lecture"),
                new Course("C02", "Database", 40, instructors[1], "Lecture"),
                new Course("C03", "Networks", 30, instructors[2], "Lecture"),
                new Course("C04", "Operating Systems", 35, instructors[0], "Lecture"),
                new Course("C05", "Software Engineering", 45, instructors[3], "Lecture"),
                new Course("C06", "Algorithms", 40, instructors[4], "Lecture"),
                new Course("C07", "Data Mining", 25, instructors[5], "Lecture"),
                new Course("C08", "Computer Graphics", 30, instructors[6], "Lab"),
                new Course("C09", "Web Programming", 25, instructors[7], "Lab"),
                new Course("C10", "Machine Learning", 35, instructors[5], "Lecture")
        };
    }

    public static Room[] createRooms() {

        return new Room[] {
                new Room("R101", 50, "Lecture"),
                new Room("R102", 40, "Lecture"),
                new Room("R103", 35, "Lecture"),
                new Room("R104", 25, "Lecture"),
                new Room("LAB1", 30, "Lab"),
                new Room("LAB2", 25, "Lab")
        };
    }
}