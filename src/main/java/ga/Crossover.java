package ga;

import model.Chromosome;

public interface Crossover {

    Chromosome[] crossover(
            Chromosome parent1,
            Chromosome parent2);
}