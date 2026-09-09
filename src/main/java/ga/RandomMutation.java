package ga;

import model.Chromosome;
import model.Gene;
import model.Room;
import model.TimeSlot;
import utils.RandomUtils;

public class RandomMutation implements Mutation {

    @Override
    public Chromosome mutate(
            Chromosome chromosome,
            TimeSlot[] slots,
            Room[] rooms) {

        Gene[] genes = chromosome.getGenes();

        int geneIndex = RandomUtils.randomIndex(genes.length);

        TimeSlot newTimeSlot = slots[RandomUtils.randomIndex(slots.length)];

        Room newRoom = rooms[RandomUtils.randomIndex(rooms.length)];

        Gene oldGene = genes[geneIndex];

        genes[geneIndex] = new Gene(
                oldGene.getCourse(),
                newTimeSlot,
                newRoom);

        return new Chromosome(genes);
    }
}