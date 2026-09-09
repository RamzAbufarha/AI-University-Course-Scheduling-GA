import data.ExperimentDataSet;
import ga.GeneticAlgorithm;
import model.Chromosome;
import model.Course;
import model.CourseGroup;
import model.Room;

public class ExperimentRunner {

        // =========================================================
        // Population Size Experiment
        // Population = 20, 50, 100, 200
        // =========================================================

        public static void runPopulationExperiment() {

                // Use the 50-course experimental dataset
                Course[] courses = ExperimentDataSet.createCourses(50);

                Room[] rooms = data.DataSet.createRooms();

                CourseGroup[] courseGroups = ExperimentDataSet.createCourseGroups(50);

                String[] preferredMorningInstructors = ExperimentDataSet.createPreferredMorningInstructors();

                int[] populationSizes = {
                                20,
                                50,
                                100,
                                200
                };

                System.out.println();
                System.out.println("===== POPULATION SIZE EXPERIMENT =====");

                System.out.printf(
                                "%-12s %-12s %-12s %-12s %-18s %-12s%n",
                                "Population",
                                "Generations",
                                "Hard",
                                "Soft",
                                "Best Fitness",
                                "Runtime(ms)");

                System.out.println(
                                "--------------------------------------------------------------------------");

                for (int populationSize : populationSizes) {

                        GeneticAlgorithm ga = new GeneticAlgorithm(
                                        populationSize,
                                        500,
                                        0.80,
                                        0.05);

                        long startTime = System.currentTimeMillis();

                        Chromosome best = ga.run(
                                        courses,
                                        rooms,
                                        courseGroups,
                                        preferredMorningInstructors);

                        long endTime = System.currentTimeMillis();

                        int hardViolations = constraints.ScheduleEvaluator.getHardViolations(
                                        best,
                                        courseGroups);

                        int softViolations = constraints.ScheduleEvaluator.getSoftViolations(
                                        best,
                                        courseGroups,
                                        preferredMorningInstructors);

                        double fitness = constraints.ScheduleEvaluator.getFitness(
                                        best,
                                        courseGroups,
                                        preferredMorningInstructors);

                        long runtime = endTime - startTime;

                        System.out.printf(
                                        "%-12d %-12d %-12d %-12d %-18.6f %-12d%n",
                                        populationSize,
                                        ga.getGenerationsUsed(),
                                        hardViolations,
                                        softViolations,
                                        fitness,
                                        runtime);
                }
        }

        // =========================================================
        // Dataset Size Experiment
        // Courses = 10, 20, 30, 40, 50
        // =========================================================

        public static void runDatasetSizeExperiment() {

                Room[] rooms = data.DataSet.createRooms();

                String[] preferredMorningInstructors = ExperimentDataSet.createPreferredMorningInstructors();

                int[] datasetSizes = {
                                10,
                                20,
                                30,
                                40,
                                50
                };

                System.out.println();
                System.out.println("===== DATASET SIZE EXPERIMENT =====");

                System.out.printf(
                                "%-12s %-12s %-12s %-12s %-18s %-12s%n",
                                "Courses",
                                "Generations",
                                "Hard",
                                "Soft",
                                "Best Fitness",
                                "Runtime(ms)");

                System.out.println(
                                "--------------------------------------------------------------------------");

                for (int datasetSize : datasetSizes) {

                        Course[] courses = ExperimentDataSet.createCourses(datasetSize);

                        CourseGroup[] courseGroups = ExperimentDataSet.createCourseGroups(datasetSize);

                        GeneticAlgorithm ga = new GeneticAlgorithm(
                                        100,
                                        500,
                                        0.80,
                                        0.05);

                        long startTime = System.currentTimeMillis();

                        Chromosome best = ga.run(
                                        courses,
                                        rooms,
                                        courseGroups,
                                        preferredMorningInstructors);

                        long endTime = System.currentTimeMillis();

                        int hardViolations = constraints.ScheduleEvaluator.getHardViolations(
                                        best,
                                        courseGroups);

                        int softViolations = constraints.ScheduleEvaluator.getSoftViolations(
                                        best,
                                        courseGroups,
                                        preferredMorningInstructors);

                        double fitness = constraints.ScheduleEvaluator.getFitness(
                                        best,
                                        courseGroups,
                                        preferredMorningInstructors);

                        long runtime = endTime - startTime;

                        System.out.printf(
                                        "%-12d %-12d %-12d %-12d %-18.6f %-12d%n",
                                        datasetSize,
                                        ga.getGenerationsUsed(),
                                        hardViolations,
                                        softViolations,
                                        fitness,
                                        runtime);
                }
        }

        // =========================================================
        // Mutation Rate Experiment
        // Mutation = 1%, 5%, 10%, 20%
        // =========================================================

        public static void runMutationExperiment() {

                // Use the 50-course experimental dataset
                Course[] courses = ExperimentDataSet.createCourses(50);

                Room[] rooms = data.DataSet.createRooms();

                CourseGroup[] courseGroups = ExperimentDataSet.createCourseGroups(50);

                String[] preferredMorningInstructors = ExperimentDataSet.createPreferredMorningInstructors();

                double[] mutationRates = {
                                0.01,
                                0.05,
                                0.10,
                                0.20
                };

                System.out.println();
                System.out.println("===== MUTATION RATE EXPERIMENT =====");

                System.out.printf(
                                "%-12s %-12s %-12s %-12s %-18s %-12s%n",
                                "Mutation",
                                "Generations",
                                "Hard",
                                "Soft",
                                "Best Fitness",
                                "Runtime(ms)");

                System.out.println(
                                "--------------------------------------------------------------------------");

                for (double mutationRate : mutationRates) {

                        GeneticAlgorithm ga = new GeneticAlgorithm(
                                        100,
                                        500,
                                        0.80,
                                        mutationRate);

                        long startTime = System.currentTimeMillis();

                        Chromosome best = ga.run(
                                        courses,
                                        rooms,
                                        courseGroups,
                                        preferredMorningInstructors);

                        long endTime = System.currentTimeMillis();

                        int hardViolations = constraints.ScheduleEvaluator.getHardViolations(
                                        best,
                                        courseGroups);

                        int softViolations = constraints.ScheduleEvaluator.getSoftViolations(
                                        best,
                                        courseGroups,
                                        preferredMorningInstructors);

                        double fitness = constraints.ScheduleEvaluator.getFitness(
                                        best,
                                        courseGroups,
                                        preferredMorningInstructors);

                        long runtime = endTime - startTime;

                        System.out.printf(
                                        "%-12.0f %-12d %-12d %-12d %-18.6f %-12d%n",
                                        mutationRate * 100,
                                        ga.getGenerationsUsed(),
                                        hardViolations,
                                        softViolations,
                                        fitness,
                                        runtime);
                }
        }

        // =========================================================
        // Crossover Rate Experiment
        // Crossover = 60%, 80%, 90%
        // =========================================================

        public static void runCrossoverExperiment() {

                // Use the 50-course experimental dataset
                Course[] courses = ExperimentDataSet.createCourses(50);

                Room[] rooms = data.DataSet.createRooms();

                CourseGroup[] courseGroups = ExperimentDataSet.createCourseGroups(50);

                String[] preferredMorningInstructors = ExperimentDataSet.createPreferredMorningInstructors();

                double[] crossoverRates = {
                                0.60,
                                0.80,
                                0.90
                };

                System.out.println();
                System.out.println("===== CROSSOVER RATE EXPERIMENT =====");

                System.out.printf(
                                "%-12s %-12s %-12s %-12s %-18s %-12s%n",
                                "Crossover",
                                "Generations",
                                "Hard",
                                "Soft",
                                "Best Fitness",
                                "Runtime(ms)");

                System.out.println(
                                "--------------------------------------------------------------------------");

                for (double crossoverRate : crossoverRates) {

                        GeneticAlgorithm ga = new GeneticAlgorithm(
                                        100,
                                        500,
                                        crossoverRate,
                                        0.05);

                        long startTime = System.currentTimeMillis();

                        Chromosome best = ga.run(
                                        courses,
                                        rooms,
                                        courseGroups,
                                        preferredMorningInstructors);

                        long endTime = System.currentTimeMillis();

                        int hardViolations = constraints.ScheduleEvaluator.getHardViolations(
                                        best,
                                        courseGroups);

                        int softViolations = constraints.ScheduleEvaluator.getSoftViolations(
                                        best,
                                        courseGroups,
                                        preferredMorningInstructors);

                        double fitness = constraints.ScheduleEvaluator.getFitness(
                                        best,
                                        courseGroups,
                                        preferredMorningInstructors);

                        long runtime = endTime - startTime;

                        System.out.printf(
                                        "%-12.0f %-12d %-12d %-12d %-18.6f %-12d%n",
                                        crossoverRate * 100,
                                        ga.getGenerationsUsed(),
                                        hardViolations,
                                        softViolations,
                                        fitness,
                                        runtime);
                }
        }

        // =========================================================
        // Main
        // =========================================================

        public static void main(String[] args) {
                runDatasetSizeExperiment();
        }
}