import data.DataSet;
import data.ExperimentDataSet;
import ga.GeneticAlgorithm;
import model.Chromosome;
import model.Course;
import model.CourseGroup;
import model.Gene;
import model.Room;
import utils.ConvergenceGraph;

public class Main {

    public static void main(String[] args) {

        // ==============================
        // 1. Load project dataset
        // ==============================

        Course[] courses = DataSet.createCourses();

        Room[] rooms = DataSet.createRooms();

        CourseGroup[] courseGroups = ExperimentDataSet.createCourseGroups(10);

        String[] preferredMorningInstructors = ExperimentDataSet
                .createPreferredMorningInstructors();

        // ==============================
        // 2. Genetic Algorithm settings
        // ==============================

        int populationSize = 100;
        int generations = 500;
        double crossoverRate = 0.80;
        double mutationRate = 0.05;

        // ==============================
        // 3. Create GA
        // ==============================

        GeneticAlgorithm ga = new GeneticAlgorithm(
                populationSize,
                generations,
                crossoverRate,
                mutationRate);

        // ==============================
        // 4. Run Genetic Algorithm
        // ==============================

        Chromosome best = ga.run(
                courses,
                rooms,
                courseGroups,
                preferredMorningInstructors);

        // ==============================
        // 5. Calculate final results
        // ==============================

        int hardViolations = constraints.ScheduleEvaluator
                .getHardViolations(
                        best,
                        courseGroups);

        int softViolations = constraints.ScheduleEvaluator
                .getSoftViolations(
                        best,
                        courseGroups,
                        preferredMorningInstructors);

        double fitness = constraints.ScheduleEvaluator
                .getFitness(
                        best,
                        courseGroups,
                        preferredMorningInstructors);

        // ==============================
        // 6. Print final timetable
        // ==============================

        System.out.println();
        System.out.println("===== FINAL TIMETABLE =====");

        System.out.printf(
                "%-15s %-15s %-25s %-20s %-10s%n",
                "Day",
                "Time",
                "Course",
                "Instructor",
                "Room");

        System.out.println(
                "--------------------------------------------------------------------------");

        for (Gene gene : best.getGenes()) {

            System.out.printf(
                    "%-15s %-15s %-25s %-20s %-10s%n",
                    gene.getTimeSlot().getDay(),
                    gene.getTimeSlot().getTime(),
                    gene.getCourse().getName(),
                    gene.getCourse()
                            .getInstructor()
                            .getName(),
                    gene.getRoom().getId());
        }

        // ==============================
        // 7. Print final results
        // ==============================

        System.out.println();
        System.out.println("===== FINAL RESULTS =====");

        System.out.println("Courses scheduled: " + courses.length + "/" + courses.length);
        System.out.println("Hard violations: " + hardViolations);
        System.out.println("Soft violations: " + softViolations);

        double finalPenalty = 100.0 * hardViolations + 10.0 * softViolations;

        System.out.println("Final penalty: " + finalPenalty);
        System.out.println("Best fitness: " + fitness);
        System.out.println("Generations used: " + ga.getGenerationsUsed());

        // ==============================
        // 8. Generate convergence graph
        // ==============================

        ConvergenceGraph.generate(
                ga.getBestFitnessHistory(),
                ga.getGenerationsUsed());

        System.out.println();
        System.out.println(
                "===== PROGRAM FINISHED =====");
    }
}