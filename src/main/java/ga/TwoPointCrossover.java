package ga;

import model.Chromosome;
import model.Gene;
import utils.RandomUtils;

public class TwoPointCrossover implements Crossover {

    @Override
    public Chromosome[] crossover(
            Chromosome parent1,
            Chromosome parent2) {

        Gene[] genes1 = parent1.getGenes();
        Gene[] genes2 = parent2.getGenes();

        int length = genes1.length;

        // Choose two different crossover points
        int point1 = RandomUtils.randomIndex(length - 1) + 1;

        int point2 = RandomUtils.randomIndex(length - 1) + 1;

        while (point2 == point1) {
            point2 = RandomUtils.randomIndex(length - 1) + 1;
        }

        // Make sure point1 is the smaller point
        if (point1 > point2) {
            int temp = point1;
            point1 = point2;
            point2 = temp;
        }

        // Create children
        Gene[] childGenes1 = new Gene[length];
        Gene[] childGenes2 = new Gene[length];

        for (int i = 0; i < length; i++) {

            if (i >= point1 && i < point2) {

                childGenes1[i] = genes2[i];
                childGenes2[i] = genes1[i];

            } else {

                childGenes1[i] = genes1[i];
                childGenes2[i] = genes2[i];
            }
        }

        Chromosome child1 = new Chromosome(childGenes1);

        Chromosome child2 = new Chromosome(childGenes2);

        return new Chromosome[] {
                child1,
                child2
        };
    }
}