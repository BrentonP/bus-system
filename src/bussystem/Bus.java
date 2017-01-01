package bussystem;

import dataaccess.Identifiable;

/**
 * Buses are assigned to a line and a time schedule.
 * @author Brenton Philp
 */
public class Bus implements Identifiable<String>{

    private final String busId;
    private Schedule timeSchedule;
    private BusLine line;

    public Bus(String busId) {
        this.busId = busId;
    }

    // Set the time the bus will arrive at the first stop
    public void setSchedule(int hour, int minute) {
        timeSchedule = new Schedule(hour, minute);
    }

    // Set the bus line assigned to this bus
    public void setBusLine(BusLine l) {
        line = l;
    }
    
    @Override
    public String getId(){
        return busId;
    }

    @Override
    public String toString() {
        return "| " + busId + " starts at " + timeSchedule + " |\n" + line;
    }
}
