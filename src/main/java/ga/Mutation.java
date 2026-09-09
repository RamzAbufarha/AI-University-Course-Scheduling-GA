package ga;

import model.Chromosome;
import model.Room;
import model.TimeSlot;

public interface Mutation {

    Chromosome mutate(
            Chromosome chromosome,
            TimeSlot[] slots,
            Room[] rooms);
}