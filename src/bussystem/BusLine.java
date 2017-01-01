package bussystem;

import java.util.ArrayList;
import java.util.List;
import dataaccess.Identifiable;

/**
 * Each bus line is defined as a series of routes (usually 2, forward and
 * return) and is identified by a number.
 *
 * @author Brenton Philp
 */
public class BusLine implements Identifiable<String> {

    private String lineNumber;
    private List<Route> routes;

    public BusLine(String lineNumber) {
        this.lineNumber = lineNumber;
        this.routes = new ArrayList<Route>();
    }

    // Add a route to this bus line
    public void addRoute(Route r) {
        routes.add(r);
    }

    // Add a route to this bus line at the indicated position
    public void addRoute(int position, Route r) {
        routes.add(position, r);
    }

    // Remove a route from this bus line
    public void deleteRoute(Route r) {
        routes.remove(r);
    }

    @Override
    public String getId() {
        return lineNumber;
    }

    @Override
    public String toString() {
        String result = "-----  " + lineNumber + "  -----";
        for (Route r : routes) {
            result += "\n" + r;
        }
        return result;
    }

}
