package constraints;

import model.Chromosome;
import model.CourseGroup;
import model.Gene;

public class SoftConstraints {

    // S1 - Avoid early classes
    public static int avoidEarlyClasses(Chromosome chromosome) {

        int violations = 0;

        Gene[] genes = chromosome.getGenes();

        for (Gene gene : genes) {

            String time = gene.getTimeSlot().getTime();

            if (time.equals("08:00-09:00")) {
                violations++;
            }
        }

        return violations;
    }

    // S2 - Avoid late classes
    public static int avoidLateClasses(Chromosome chromosome) {

        int violations = 0;

        Gene[] genes = chromosome.getGenes();

        for (Gene gene : genes) {

            String time = gene.getTimeSlot().getTime();

            if (time.equals("11:00-12:00")) {
                violations++;
            }
        }

        return violations;
    }

    // S3 - Minimize student gaps
    public static int studentGaps(Chromosome chromosome,
            CourseGroup[] courseGroups) {

        int violations = 0;

        Gene[] genes = chromosome.getGenes();

        for (int i = 0; i < genes.length; i++) {

            for (int j = i + 1; j < genes.length; j++) {

                String course1 = genes[i].getCourse().getId();
                String course2 = genes[j].getCourse().getId();

                String group1 = getGroupId(course1, courseGroups);
                String group2 = getGroupId(course2, courseGroups);

                if (group1 != null && group1.equals(group2)) {

                    String day1 = genes[i].getTimeSlot().getDay();
                    String day2 = genes[j].getTimeSlot().getDay();

                    if (day1.equals(day2)) {

                        int time1 = getTimeIndex(
                                genes[i].getTimeSlot().getTime());

                        int time2 = getTimeIndex(
                                genes[j].getTimeSlot().getTime());

                        if (Math.abs(time1 - time2) == 2) {
                            violations++;
                        }
                    }
                }
            }
        }

        return violations;
    }

    // S4 - Instructor preferences
    public static int instructorPreferences(Chromosome chromosome,
            String[] preferredMorningInstructors) {

        int violations = 0;

        Gene[] genes = chromosome.getGenes();

        for (Gene gene : genes) {

            String instructorId = gene.getCourse().getInstructor().getId();

            String time = gene.getTimeSlot().getTime();

            if (isPreferredMorning(instructorId,
                    preferredMorningInstructors)) {

                if (time.equals("11:00-12:00")) {
                    violations++;
                }
            }
        }

        return violations;
    }

    // S5 - Minimize number of working days
    public static int workingDays(Chromosome chromosome,
            CourseGroup[] courseGroups) {

        int violations = 0;

        String[] days = {
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday"
        };

        String[] groups = getGroups(courseGroups);

        for (String group : groups) {

            int numberOfDays = 0;

            for (String day : days) {

                if (hasCourseOnDay(
                        chromosome,
                        courseGroups,
                        group,
                        day)) {

                    numberOfDays++;
                }
            }

            if (numberOfDays == 5) {
                violations++;
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

    private static int getTimeIndex(String time) {

        if (time.equals("08:00-09:00")) {
            return 0;
        }

        if (time.equals("09:00-10:00")) {
            return 1;
        }

        if (time.equals("10:00-11:00")) {
            return 2;
        }

        if (time.equals("11:00-12:00")) {
            return 3;
        }

        return -1;
    }

    private static boolean isPreferredMorning(
            String instructorId,
            String[] preferredMorningInstructors) {

        for (String id : preferredMorningInstructors) {

            if (id.equals(instructorId)) {
                return true;
            }
        }

        return false;
    }

    private static String[] getGroups(
            CourseGroup[] courseGroups) {

        String[] groups = new String[courseGroups.length];

        int count = 0;

        for (CourseGroup courseGroup : courseGroups) {

            boolean exists = false;

            for (int i = 0; i < count; i++) {

                if (groups[i].equals(courseGroup.getGroupId())) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                groups[count] = courseGroup.getGroupId();
                count++;
            }
        }

        String[] result = new String[count];

        for (int i = 0; i < count; i++) {
            result[i] = groups[i];
        }

        return result;
    }

    private static boolean hasCourseOnDay(
            Chromosome chromosome,
            CourseGroup[] courseGroups,
            String groupId,
            String day) {

        Gene[] genes = chromosome.getGenes();

        for (Gene gene : genes) {

            String courseId = gene.getCourse().getId();

            String courseGroup = getGroupId(courseId, courseGroups);

            if (groupId.equals(courseGroup)
                    && day.equals(gene.getTimeSlot().getDay())) {

                return true;
            }
        }

        return false;
    }

    public static int totalSoftViolations(
            Chromosome chromosome,
            CourseGroup[] courseGroups,
            String[] preferredMorningInstructors) {

        int violations = 0;

        violations += avoidEarlyClasses(chromosome);

        violations += avoidLateClasses(chromosome);

        violations += studentGaps(
                chromosome,
                courseGroups);

        violations += instructorPreferences(
                chromosome,
                preferredMorningInstructors);

        violations += workingDays(
                chromosome,
                courseGroups);

        return violations;
    }
}