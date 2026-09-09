package ga;

import model.Chromosome;
import model.Course;
import model.Gene;
import model.Room;
import model.TimeSlot;
import utils.RandomUtils;
import utils.SlotGenerator;

public class Population {

    private Chromosome[] chromosomes;

    public Population(Chromosome[] chromosomes) {
        this.chromosomes = chromosomes;
    }

    public Chromosome[] getChromosomes() {
        return chromosomes;
    }

    public int size() {
        return chromosomes.length;
    }

    public static Population generateInitialPopulation(
            Course[] courses,
            Room[] rooms,
            int populationSize) {

        Chromosome[] chromosomes = new Chromosome[populationSize];

        TimeSlot[] slots = SlotGenerator.generateSlots();

        for (int i = 0; i < populationSize; i++) {

            Gene[] genes = new Gene[courses.length];

            for (int j = 0; j < courses.length; j++) {

                Course course = courses[j];

                TimeSlot slot = slots[RandomUtils.randomIndex(slots.length)];

                Room room = rooms[RandomUtils.randomIndex(rooms.length)];

                genes[j] = new Gene(course, slot, room);
            }

            chromosomes[i] = new Chromosome(genes);
        }

        return new Population(chromosomes);
    }
}