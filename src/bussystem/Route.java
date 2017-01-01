package bussystem;

import java.util.ArrayList;
import java.util.List;
import dataaccess.Identifiable;

/**
 * Each route is defined by a sequence of stops and is identified by a name.
 * 
 * Note - To handle forward and return routes, we can't simply keep track of a
 * direction because return routes don't necessarily use the same stops. A new
 * route will need to be created for the return journey.
 *
 * @author Brenton Philp
 */
public class Route implements Identifiable<String> {

    private final String routeName;
    private List<Stop> stops;

    public Route(String routeName) {
        this.routeName = routeName;
        this.stops = new ArrayList();
    }

    // Add a stop at the indicated position in the list
    public void addStop(int position, Stop stop) {
        stops.add(position, stop);
    }

    // Add a stop to the end of the route
    public void addStop(Stop stop) {
        stops.add(stop);
    }

    // Remove the indicated stop from the route
    public void removeStop(Stop stop) {
        stops.remove(stop);
    }

    // Return all the stops used on this route
    public List<Stop> getAllStops() {
        return stops;
    }

    @Override
    public String getId() {
        return routeName;
    }

    /**
     * Convert this route to a String of the form: routeName (Stop1, Stop2)
     *
     * @return The route name, followed by the stop codes in brackets.
     */
    @Override
    public String toString() {
        String result = routeName + " (";

        for (int i = 0; i < stops.size(); i++) {
            if (i != 0) {
                result += ", ";
            }
            result += stops.get(i);
        }

        result += ")";
        return result;
    }

}
