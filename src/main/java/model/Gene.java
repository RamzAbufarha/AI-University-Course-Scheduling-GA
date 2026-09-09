package model;

public class Gene {

    private Course course;
    private TimeSlot timeSlot;
    private Room room;

    public Gene(Course course, TimeSlot timeSlot, Room room) {
        this.course = course;
        this.timeSlot = timeSlot;
        this.room = room;
    }

    public Course getCourse() {
        return course;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public Room getRoom() {
        return room;
    }
}