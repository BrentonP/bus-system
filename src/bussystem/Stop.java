package bussystem;

import dataaccess.Identifiable;


public class Stop implements Identifiable<String> {

    // Each stop is identified by a code.
    private final String stopCode;

    public Stop(String stopCode) {
        this.stopCode = stopCode;
    }

    @Override
    public String getId() {
        return stopCode;
    }

    @Override
    public String toString() {
        return stopCode;
    }
}
