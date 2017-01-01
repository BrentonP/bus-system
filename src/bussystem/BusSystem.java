package bussystem;

import dataaccess.GenericDao;
import dataaccess.GenericDaoImpl;

/**
 *
 * @author Brenton Philp
 */
public class BusSystem {


    public static void main(String[] args) {

        // Connect to our 'databases'
        GenericDao<Stop, String> stopDB = new GenericDaoImpl<>();
        GenericDao<Route, String> routeDB = new GenericDaoImpl<>();
        GenericDao<BusLine, String> busLineDB = new GenericDaoImpl<>();
        GenericDao<Bus, String> busDB = new GenericDaoImpl<>();

        // Create all the stops
        stopDB.create(new Stop("001-NORTHTCE"));
        stopDB.create(new Stop("002-WESTTCE"));
        stopDB.create(new Stop("003-PORTRD"));
        stopDB.create(new Stop("004-SOUTHRD"));
        stopDB.create(new Stop("005-ARNDALE"));
        stopDB.create(new Stop("006-WOODVILLERD"));
        stopDB.create(new Stop("007-PORTADL"));
        stopDB.create(new Stop("008-BELAIRRD"));

        // Define a route in the system
        Route adlToPtAdl = new Route("Adelaide to Port Adelaide");
        adlToPtAdl.addStop(stopDB.findById("001-NORTHTCE"));
        adlToPtAdl.addStop(stopDB.findById("002-WESTTCE"));
        adlToPtAdl.addStop(stopDB.findById("003-PORTRD"));
        adlToPtAdl.addStop(stopDB.findById("004-SOUTHRD"));
        adlToPtAdl.addStop(stopDB.findById("005-ARNDALE"));
        adlToPtAdl.addStop(stopDB.findById("007-PORTADL"));
        routeDB.create(adlToPtAdl);

        // Define a route in the system
        Route ptAdlToAdl = new Route("Port Adelaide to Adelaide");
        ptAdlToAdl.addStop(stopDB.findById("007-PORTADL"));
        ptAdlToAdl.addStop(stopDB.findById("006-WOODVILLERD"));
        ptAdlToAdl.addStop(stopDB.findById("004-SOUTHRD"));
        ptAdlToAdl.addStop(stopDB.findById("003-PORTRD"));
        ptAdlToAdl.addStop(stopDB.findById("001-NORTHTCE"));
        routeDB.create(ptAdlToAdl);

        // Define a route in the system
        Route ptAdlToAdlEx = new Route("Port Adelaide to Adelaide Express");
        ptAdlToAdlEx.addStop(stopDB.findById("007-PORTADL"));
        ptAdlToAdlEx.addStop(stopDB.findById("008-BELAIRRD")); // Oops! Accidentally added the wrong stop
        ptAdlToAdlEx.addStop(stopDB.findById("001-NORTHTCE"));
        routeDB.create(ptAdlToAdlEx);
        ptAdlToAdlEx.removeStop(stopDB.findById("008-BELAIRRD")); // Remove the stop in Belair
        routeDB.update(ptAdlToAdlEx);

        // Define a route in the system
        Route adlToPtAdlEx = new Route("Adelaide to Port Adelaide Express");
        adlToPtAdlEx.addStop(stopDB.findById("001-NORTHTCE"));
        adlToPtAdlEx.addStop(stopDB.findById("007-PORTADL"));
        routeDB.create(adlToPtAdlEx);

        // Define a new bus line
        BusLine line254 = new BusLine("254");
        line254.addRoute(routeDB.findById("Adelaide to Port Adelaide"));
        line254.addRoute(routeDB.findById("Port Adelaide to Adelaide"));
        busLineDB.create(line254);

        // Define a new bus line
        BusLine line254X = new BusLine("254X");
        line254X.addRoute(routeDB.findById("Adelaide to Port Adelaide Express"));
        line254X.addRoute(routeDB.findById("Port Adelaide to Adelaide Express"));
        busLineDB.create(line254X);

        // Define a new bus
        Bus bus1 = new Bus("B001");
        bus1.setBusLine(busLineDB.findById("254X"));
        bus1.setSchedule(9, 30); // add the schedule for this bus
        busDB.create(bus1);

        // Define another bus
        Bus bus2 = new Bus("B002");
        bus2.setBusLine(busLineDB.findById("254"));
        bus2.setSchedule(6, 00); // add the schedule for this bus
        busDB.create(bus2);

        //-----------------------------------------------------
        // List all stops in the system
        System.out.println("ALL STOPS IN THE SYSTEM");
        for (Stop s : stopDB.findAll()) {
            System.out.println(s);
        }
        System.out.println();

        // List all routes in the system
        System.out.println("ALL ROUTES IN THE SYSTEM");
        for (Route r : routeDB.findAll()) {
            System.out.println(r);
        }
        System.out.println();

        // List all lines in the system
        System.out.println("ALL LINES IN THE SYSTEM");
        for (BusLine l : busLineDB.findAll()) {
            System.out.println(l);
        }
        System.out.println();

        // List all busses in the system
        System.out.println("ALL BUSSES IN THE SYSTEM");
        for (Bus b : busDB.findAll()) {
            System.out.println(b);
        }
        System.out.println();

    }

    /* The output from running this is:

ALL STOPS IN THE SYSTEM
001-NORTHTCE
002-WESTTCE
008-BELAIRRD
006-WOODVILLERD
007-PORTADL
003-PORTRD
004-SOUTHRD
005-ARNDALE

ALL ROUTES IN THE SYSTEM
Adelaide to Port Adelaide Express (001-NORTHTCE, 007-PORTADL)
Adelaide to Port Adelaide (001-NORTHTCE, 002-WESTTCE, 003-PORTRD, 004-SOUTHRD, 005-ARNDALE, 007-PORTADL)
Port Adelaide to Adelaide (007-PORTADL, 006-WOODVILLERD, 004-SOUTHRD, 003-PORTRD, 001-NORTHTCE)
Port Adelaide to Adelaide Express (007-PORTADL, 001-NORTHTCE)

ALL LINES IN THE SYSTEM
-----  254X  -----
Adelaide to Port Adelaide Express (001-NORTHTCE, 007-PORTADL)
Port Adelaide to Adelaide Express (007-PORTADL, 001-NORTHTCE)
-----  254  -----
Adelaide to Port Adelaide (001-NORTHTCE, 002-WESTTCE, 003-PORTRD, 004-SOUTHRD, 005-ARNDALE, 007-PORTADL)
Port Adelaide to Adelaide (007-PORTADL, 006-WOODVILLERD, 004-SOUTHRD, 003-PORTRD, 001-NORTHTCE)

ALL BUSSES IN THE SYSTEM
| B001 starts at 09:30 |
-----  254X  -----
Adelaide to Port Adelaide Express (001-NORTHTCE, 007-PORTADL)
Port Adelaide to Adelaide Express (007-PORTADL, 001-NORTHTCE)
| B002 starts at 06:00 |
-----  254  -----
Adelaide to Port Adelaide (001-NORTHTCE, 002-WESTTCE, 003-PORTRD, 004-SOUTHRD, 005-ARNDALE, 007-PORTADL)
Port Adelaide to Adelaide (007-PORTADL, 006-WOODVILLERD, 004-SOUTHRD, 003-PORTRD, 001-NORTHTCE)
    
    
     */
}
