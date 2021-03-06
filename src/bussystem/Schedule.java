package bussystem;

/**
 * The time schedule is defined and identified by the arrival time at the first
 * stop.
 *
 * @author Brenton Philp
 */
public class Schedule {

    // time is in 24 hour time
    private int hour;
    private int minute;

    public Schedule(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
}
