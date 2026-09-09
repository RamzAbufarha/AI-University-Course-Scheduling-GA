package utils;

import model.TimeSlot;

public class SlotGenerator {

    public static TimeSlot[] generateSlots() {

        String[] days = {
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday"
        };

        String[] times = {
                "08:00-09:00",
                "09:00-10:00",
                "10:00-11:00",
                "11:00-12:00"
        };

        TimeSlot[] slots = new TimeSlot[20];

        int index = 0;

        for (String day : days) {
            for (String time : times) {
                slots[index] = new TimeSlot(day, time);
                index++;
            }
        }

        return slots;
    }
}