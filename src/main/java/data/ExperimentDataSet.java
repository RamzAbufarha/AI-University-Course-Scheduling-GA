package data;

import model.Course;
import model.CourseGroup;
import model.Instructor;

public class ExperimentDataSet {

    // Generate a dataset with the requested number of courses
    public static Course[] createCourses(int numberOfCourses) {

        if (numberOfCourses < 10) {
            numberOfCourses = 10;
        }

        if (numberOfCourses > 50) {
            numberOfCourses = 50;
        }

        // Keep the original 10 project courses
        Course[] originalCourses = DataSet.createCourses();

        if (numberOfCourses == 10) {
            return originalCourses;
        }

        Instructor[] instructors = DataSet.createInstructors();

        Course[] courses = new Course[numberOfCourses];

        // Copy the original 10 courses
        for (int i = 0; i < 10; i++) {

            courses[i] = originalCourses[i];
        }

        // Generate additional experimental courses
        for (int i = 10; i < numberOfCourses; i++) {

            String courseId = String.format("C%02d", i + 1);

            String courseName = "Course " + (i + 1);

            String type;

            // Every 10th additional course is a Lab
            if (i % 10 == 0) {

                type = "Lab";

            } else {

                type = "Lecture";
            }

            int students;

            // Lab rooms have capacities 25 and 30.
            // Therefore Lab courses must have <= 30 students.
            if (type.equals("Lab")) {

                students = 20 + (i % 3) * 5;

            } else {

                // Lecture rooms have capacities up to 50.
                // Therefore Lecture courses stay <= 50 students.
                students = 25 + (i % 5) * 5;
            }

            Instructor instructor = instructors[i % instructors.length];

            courses[i] = new Course(
                    courseId,
                    courseName,
                    students,
                    instructor,
                    type);
        }

        return courses;
    }

    // Generate student-group assignments
    public static CourseGroup[] createCourseGroups(
            int numberOfCourses) {

        Course[] courses = createCourses(numberOfCourses);

        CourseGroup[] courseGroups = new CourseGroup[numberOfCourses];

        String[] groups = {
                "G1",
                "G2",
                "G3",
                "G4",
                "G5"
        };

        for (int i = 0; i < courses.length; i++) {

            courseGroups[i] = new CourseGroup(
                    courses[i].getId(),
                    groups[i % groups.length]);
        }

        return courseGroups;
    }

    // Experimental instructor preferences
    public static String[] createPreferredMorningInstructors() {

        return new String[] {
                "I01",
                "I03",
                "I06"
        };
    }
}