package ga;

public class FitnessFunction {

    private static final double HARD_WEIGHT = 100.0;
    private static final double SOFT_WEIGHT = 10.0;

    public static double calculateFitness(int hardViolations,
            int softViolations) {

        double penalty = HARD_WEIGHT * hardViolations
                + SOFT_WEIGHT * softViolations;

        return 1.0 / (1.0 + penalty);
    }
}