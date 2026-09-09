package ga;

import constraints.ScheduleEvaluator;
import model.Chromosome;
import model.CourseGroup;

public class Elitism {

    public static Chromosome[] keepBest(
            Population population,
            int numberOfElite,
            CourseGroup[] courseGroups,
            String[] preferredMorningInstructors) {

        Chromosome[] chromosomes = population.getChromosomes();

        Chromosome[] elite = new Chromosome[numberOfElite];

        boolean[] selected = new boolean[chromosomes.length];

        for (int i = 0; i < numberOfElite; i++) {

            int bestIndex = -1;
            double bestFitness = -1;

            for (int j = 0; j < chromosomes.length; j++) {

                if (!selected[j]) {

                    double fitness = ScheduleEvaluator.getFitness(
                            chromosomes[j],
                            courseGroups,
                            preferredMorningInstructors);

                    if (fitness > bestFitness) {
                        bestFitness = fitness;
                        bestIndex = j;
                    }
                }
            }

            elite[i] = chromosomes[bestIndex];
            selected[bestIndex] = true;
        }

        return elite;
    }
}