package constraints;

import model.Chromosome;
import model.CourseGroup;
import ga.FitnessFunction;

public class ScheduleEvaluator {

    public static int getHardViolations(
            Chromosome chromosome,
            CourseGroup[] courseGroups) {

        int violations = 0;

        violations += HardConstraints.instructorConflict(chromosome);

        violations += HardConstraints.roomConflict(chromosome);

        violations += HardConstraints.studentConflict(
                chromosome,
                courseGroups);

        violations += HardConstraints.roomCapacity(chromosome);

        violations += HardConstraints.roomType(chromosome);

        violations += HardConstraints.instructorAvailability(
                chromosome);

        return violations;
    }

    public static int getSoftViolations(
            Chromosome chromosome,
            CourseGroup[] courseGroups,
            String[] preferredMorningInstructors) {

        return SoftConstraints.totalSoftViolations(
                chromosome,
                courseGroups,
                preferredMorningInstructors);
    }

    public static double getFitness(
            Chromosome chromosome,
            CourseGroup[] courseGroups,
            String[] preferredMorningInstructors) {

        int hardViolations = getHardViolations(
                chromosome,
                courseGroups);

        int softViolations = getSoftViolations(
                chromosome,
                courseGroups,
                preferredMorningInstructors);

        return FitnessFunction.calculateFitness(
                hardViolations,
                softViolations);
    }
}