package airport.cac226.rxr353;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

public final class RouteFinder {
    
    private final Set<Airport> airports;

     
    private RouteFinder(Set<Airport> airports) {
        this.airports = airports;
    }
    
    public RouteFinder of(Set<Airport> airport){
        Objects.requireNonNull(airports);
        
        return new RouteFinder(airport);
    }
    
    public final RouteNode route(Airport origin, Airport destination, LocalTime departureTime, FareClass fareClass){
        Objects.requireNonNull(origin, "Airport origin must not be null");
        Objects.requireNonNull(destination, "Airport destination must not be null");
        Objects.requireNonNull(departureTime, "Departure time must not be null");
        Objects.requireNonNull(fareClass, "Fare class must not be null");

        //RouteState routeState = RouteState.of()


        /*
        * Algorithm route(origin, destination, departureTime, fareClass):
            routeState ← new route state in which airports have no previous node and arrival times
            * are unknown except for the departure airport, whose time is equal to departureTime
            while (there is an unreached airport)
                currentNode ← unreached airport with the smallest arrival time
            if (currentNode = destination) return currentNode
            for all available flights from the currentNode do
            if flight’s arrival time < arrival time at the flight’s destination
            replace the destination node with one that contains the flight’s arrival time and the flight’s departure airport.
            return null // a route was not found
        * */
        //return RouteNode.of(origin, departureTime, fareClass);

        return null;
    }
}
