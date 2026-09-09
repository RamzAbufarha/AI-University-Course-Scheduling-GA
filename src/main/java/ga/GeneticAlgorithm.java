package ga;

import constraints.RoomRepair;
import constraints.ScheduleEvaluator;
import model.Chromosome;
import model.Course;
import model.CourseGroup;
import model.Gene;
import model.Room;
import model.TimeSlot;
import utils.SlotGenerator;

public class GeneticAlgorithm {

    private int populationSize;
    private int maxGenerations;
    private double crossoverRate;
    private double mutationRate;
    private double[] bestFitnessHistory;
    private int generationsUsed;

    public GeneticAlgorithm(int populationSize,
            int maxGenerations,
            double crossoverRate,
            double mutationRate) {

        this.populationSize = populationSize;
        this.maxGenerations = maxGenerations;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int getMaxGenerations() {
        return maxGenerations;
    }

    public double getCrossoverRate() {
        return crossoverRate;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public double[] getBestFitnessHistory() {
        return bestFitnessHistory;
    }

    public int getGenerationsUsed() {
        return generationsUsed;
    }

    public Chromosome run(
            Course[] courses,
            Room[] rooms,
            CourseGroup[] courseGroups,
            String[] preferredMorningInstructors) {

        // 1. Generate initial population
        Population population = Population.generateInitialPopulation(
                courses,
                rooms,
                populationSize);

        TimeSlot[] slots = SlotGenerator.generateSlots();

        // 2. Create GA operators
        TournamentSelection selection = new TournamentSelection(3);

        TwoPointCrossover crossover = new TwoPointCrossover();

        RandomMutation mutation = new RandomMutation();

        // 3. Find initial best chromosome
        Chromosome best = getBestChromosome(
                population,
                courseGroups,
                preferredMorningInstructors);

        // Create fitness history
        bestFitnessHistory = new double[maxGenerations + 1];

        bestFitnessHistory[0] = ScheduleEvaluator.getFitness(
                best,
                courseGroups,
                preferredMorningInstructors);

        // Count generations without improvement
        int generationsWithoutImprovement = 0;

        generationsUsed = 0;

        // 4. Repeat for each generation
        for (int generation = 0; generation < maxGenerations; generation++) {

            // 5. Keep elite chromosomes
            int numberOfElite = Math.max(
                    1,
                    (int) Math.round(
                            populationSize * 0.05));

            Chromosome[] elite = Elitism.keepBest(
                    population,
                    numberOfElite,
                    courseGroups,
                    preferredMorningInstructors);

            // 6. Create new population
            Chromosome[] newChromosomes = new Chromosome[populationSize];

            int count = 0;

            // Add elite chromosomes first
            for (Chromosome chromosome : elite) {

                if (count < populationSize) {

                    newChromosomes[count] = copyChromosome(chromosome);

                    count++;
                }
            }

            // 7. Fill the rest of the population
            while (count < populationSize) {

                Chromosome parent1 = selection.select(
                        population,
                        courseGroups,
                        preferredMorningInstructors);

                Chromosome parent2 = selection.select(
                        population,
                        courseGroups,
                        preferredMorningInstructors);

                Chromosome[] children;

                // 8. Crossover
                if (Math.random() < crossoverRate) {

                    children = crossover.crossover(
                            parent1,
                            parent2);

                } else {

                    children = new Chromosome[] {
                            copyChromosome(parent1),
                            copyChromosome(parent2)
                    };
                }

                // 9. Mutation
                for (Chromosome child : children) {

                    if (count >= populationSize) {
                        break;
                    }

                    Chromosome finalChild = child;

                    if (Math.random() < mutationRate) {

                        finalChild = mutation.mutate(
                                child,
                                slots,
                                rooms);
                    }

                    // Repair room assignment
                    finalChild = RoomRepair.repair(
                            finalChild,
                            rooms);

                    newChromosomes[count] = finalChild;
                    count++;
                }
            }

            // 10. Replace old population
            population = new Population(newChromosomes);

            // 11. Find best chromosome in new population
            Chromosome generationBest = getBestChromosome(
                    population,
                    courseGroups,
                    preferredMorningInstructors);

            double generationBestFitness = ScheduleEvaluator.getFitness(
                    generationBest,
                    courseGroups,
                    preferredMorningInstructors);

            double bestFitness = ScheduleEvaluator.getFitness(
                    best,
                    courseGroups,
                    preferredMorningInstructors);

            // Update global best
            if (generationBestFitness > bestFitness) {

                best = generationBest;

                generationsWithoutImprovement = 0;

            } else {

                generationsWithoutImprovement++;
            }

            // Save best fitness
            bestFitnessHistory[generation + 1] = ScheduleEvaluator.getFitness(
                    best,
                    courseGroups,
                    preferredMorningInstructors);

            // Update number of generations used
            generationsUsed = generation + 1;

            // Termination condition:
            // Stop if there was no improvement
            // for 50 generations
            if (generationsWithoutImprovement >= 50) {
                break;
            }
        }

        // 12. Return best solution
        return best;
    }

    private Chromosome getBestChromosome(
            Population population,
            CourseGroup[] courseGroups,
            String[] preferredMorningInstructors) {

        Chromosome best = population.getChromosomes()[0];

        double bestFitness = ScheduleEvaluator.getFitness(
                best,
                courseGroups,
                preferredMorningInstructors);

        for (int i = 1; i < population.size(); i++) {

            Chromosome current = population.getChromosomes()[i];

            double currentFitness = ScheduleEvaluator.getFitness(
                    current,
                    courseGroups,
                    preferredMorningInstructors);

            if (currentFitness > bestFitness) {

                best = current;
                bestFitness = currentFitness;
            }
        }

        return best;
    }

    private Chromosome copyChromosome(
            Chromosome chromosome) {

        Gene[] oldGenes = chromosome.getGenes();

        Gene[] newGenes = new Gene[oldGenes.length];

        for (int i = 0; i < oldGenes.length; i++) {

            Gene oldGene = oldGenes[i];

            newGenes[i] = new Gene(
                    oldGene.getCourse(),
                    oldGene.getTimeSlot(),
                    oldGene.getRoom());
        }

        return new Chromosome(newGenes);
    }
}