package constraints;

import model.Chromosome;
import model.CourseGroup;
import model.Gene;
import model.Instructor;
import model.TimeSlot;

public class HardConstraints {

    public static int instructorConflict(Chromosome chromosome) {

        int violations = 0;

        Gene[] genes = chromosome.getGenes();

        for (int i = 0; i < genes.length; i++) {

            for (int j = i + 1; j < genes.length; j++) {

                String instructor1 = genes[i].getCourse().getInstructor().getId();

                String instructor2 = genes[j].getCourse().getInstructor().getId();

                String day1 = genes[i].getTimeSlot().getDay();

                String day2 = genes[j].getTimeSlot().getDay();

                String time1 = genes[i].getTimeSlot().getTime();

                String time2 = genes[j].getTimeSlot().getTime();

                if (instructor1.equals(instructor2)
                        && day1.equals(day2)
                        && time1.equals(time2)) {

                    violations++;
                }
            }
        }

        return violations;
    }

    public static int roomConflict(Chromosome chromosome) {

        int violations = 0;

        Gene[] genes = chromosome.getGenes();

        for (int i = 0; i < genes.length; i++) {

            for (int j = i + 1; j < genes.length; j++) {

                String room1 = genes[i].getRoom().getId();

                String room2 = genes[j].getRoom().getId();

                String day1 = genes[i].getTimeSlot().getDay();

                String day2 = genes[j].getTimeSlot().getDay();

                String time1 = genes[i].getTimeSlot().getTime();

                String time2 = genes[j].getTimeSlot().getTime();

                if (room1.equals(room2)
                        && day1.equals(day2)
                        && time1.equals(time2)) {

                    violations++;
                }
            }
        }

        return violations;
    }

    public static int roomCapacity(Chromosome chromosome) {

        int violations = 0;

        Gene[] genes = chromosome.getGenes();

        for (Gene gene : genes) {

            int students = gene.getCourse().getStudents();
            int capacity = gene.getRoom().getCapacity();

            if (students > capacity) {
                violations++;
            }
        }

        return violations;
    }

    public static int roomType(Chromosome chromosome) {

        int violations = 0;

        Gene[] genes = chromosome.getGenes();

        for (Gene gene : genes) {

            String courseType = gene.getCourse().getType();
            String roomType = gene.getRoom().getType();

            if (!courseType.equals(roomType)) {
                violations++;
            }
        }

        return violations;
    }

    public static int instructorAvailability(Chromosome chromosome) {

        int violations = 0;

        Gene[] genes = chromosome.getGenes();

        for (Gene gene : genes) {

            Instructor instructor = gene.getCourse().getInstructor();

            TimeSlot timeSlot = gene.getTimeSlot();

            if (!InstructorAvailability.isAvailable(
                    instructor, timeSlot)) {

                violations++;
            }
        }

        return violations;
    }

    public static int totalHardViolations(Chromosome chromosome) {

        int violations = 0;

        violations += instructorConflict(chromosome);
        violations += roomConflict(chromosome);
        violations += roomCapacity(chromosome);
        violations += roomType(chromosome);
        violations += instructorAvailability(chromosome);

        return violations;
    }

    public static int studentConflict(Chromosome chromosome,
            CourseGroup[] courseGroups) {

        int violations = 0;

        Gene[] genes = chromosome.getGenes();

        for (int i = 0; i < genes.length; i++) {

            for (int j = i + 1; j < genes.length; j++) {

                String course1 = genes[i].getCourse().getId();
                String course2 = genes[j].getCourse().getId();

                String group1 = getGroupId(course1, courseGroups);
                String group2 = getGroupId(course2, courseGroups);

                if (group1 != null
                        && group1.equals(group2)) {

                    String day1 = genes[i].getTimeSlot().getDay();
                    String day2 = genes[j].getTimeSlot().getDay();

                    String time1 = genes[i].getTimeSlot().getTime();
                    String time2 = genes[j].getTimeSlot().getTime();

                    if (day1.equals(day2) && time1.equals(time2)) {
                        violations++;
                    }
                }
            }
        }

        return violations;
    }

    private static String getGroupId(String courseId,
            CourseGroup[] courseGroups) {

        for (CourseGroup courseGroup : courseGroups) {

            if (courseGroup.getCourseId().equals(courseId)) {
                return courseGroup.getGroupId();
            }
        }

        return null;
    }
}
