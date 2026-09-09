package constraints;

import model.Instructor;
import model.TimeSlot;

public class InstructorAvailability {

    public static boolean isAvailable(Instructor instructor,
            TimeSlot timeSlot) {

        if (instructor.getId().equals("I01")
                && timeSlot.getDay().equals("Monday")
                && timeSlot.getTime().equals("10:00-11:00")) {

            return false;
        }

        return true;
    }
}