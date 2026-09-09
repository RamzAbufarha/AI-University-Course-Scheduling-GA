package constraints;

import model.Chromosome;
import model.Gene;
import model.Room;

public class RoomRepair {

    public static Chromosome repair(
            Chromosome chromosome,
            Room[] rooms) {

        Gene[] oldGenes = chromosome.getGenes();

        Gene[] newGenes = new Gene[oldGenes.length];

        for (int i = 0; i < oldGenes.length; i++) {

            Gene oldGene = oldGenes[i];

            String courseType = oldGene.getCourse().getType();

            int students = oldGene.getCourse().getStudents();

            Room suitableRoom = findSuitableRoom(
                    oldGene,
                    newGenes,
                    i,
                    rooms,
                    courseType,
                    students);

            if (suitableRoom != null) {

                newGenes[i] = new Gene(
                        oldGene.getCourse(),
                        oldGene.getTimeSlot(),
                        suitableRoom);

            } else {

                // Keep the original room if no
                // suitable non-conflicting room exists
                newGenes[i] = new Gene(
                        oldGene.getCourse(),
                        oldGene.getTimeSlot(),
                        oldGene.getRoom());
            }
        }

        return new Chromosome(newGenes);
    }

    private static Room findSuitableRoom(
            Gene currentGene,
            Gene[] newGenes,
            int currentIndex,
            Room[] rooms,
            String courseType,
            int students) {

        for (Room room : rooms) {

            // Check room type
            boolean correctType = room.getType().equals(courseType);

            // Check room capacity
            boolean enoughCapacity = room.getCapacity() >= students;

            if (!correctType || !enoughCapacity) {
                continue;
            }

            // Check room conflict
            boolean roomAlreadyUsed = isRoomUsedAtSameTime(
                    room,
                    currentGene,
                    newGenes,
                    currentIndex);

            if (!roomAlreadyUsed) {
                return room;
            }
        }

        return null;
    }

    private static boolean isRoomUsedAtSameTime(
            Room room,
            Gene currentGene,
            Gene[] newGenes,
            int currentIndex) {

        String currentDay = currentGene.getTimeSlot().getDay();

        String currentTime = currentGene.getTimeSlot().getTime();

        for (int i = 0; i < currentIndex; i++) {

            Gene previousGene = newGenes[i];

            if (previousGene == null) {
                continue;
            }

            String previousDay = previousGene.getTimeSlot().getDay();

            String previousTime = previousGene.getTimeSlot().getTime();

            String previousRoom = previousGene.getRoom().getId();

            if (room.getId().equals(previousRoom)
                    && currentDay.equals(previousDay)
                    && currentTime.equals(previousTime)) {

                return true;
            }
        }

        return false;
    }
}