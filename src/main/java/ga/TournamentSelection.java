package ga;

import model.Chromosome;
import model.CourseGroup;
import utils.RandomUtils;
import constraints.ScheduleEvaluator;

public class TournamentSelection implements Selection {

    private int tournamentSize;

    public TournamentSelection(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    @Override
    public Chromosome select(
            Population population,
            CourseGroup[] courseGroups,
            String[] preferredMorningInstructors) {

        Chromosome best = null;
        double bestFitness = -1;

        for (int i = 0; i < tournamentSize; i++) {

            int randomIndex = RandomUtils.randomIndex(population.size());

            Chromosome candidate = population.getChromosomes()[randomIndex];

            double candidateFitness = ScheduleEvaluator.getFitness(
                    candidate,
                    courseGroups,
                    preferredMorningInstructors);

            if (candidateFitness > bestFitness) {

                bestFitness = candidateFitness;
                best = candidate;
            }
        }

        return best;
    }
}