package airport.cac226.rxr353;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

public final class RouteFinder {
    
    private final Set<Airport> airports;

    private RouteFinder(Set<Airport> airports) {
        this.airports = airports;
    }
    
    public static RouteFinder of(Set<Airport> airports){
        Objects.requireNonNull(airports);
        
        return new RouteFinder(airports);
    }

    public final RouteNode route(Airport origin, Airport destination, LocalTime departureTime, FareClass fareClass){
        Objects.requireNonNull(origin, "Airport origin must not be null");
        Objects.requireNonNull(destination, "Airport destination must not be null");
        Objects.requireNonNull(departureTime, "Departure time must not be null");
        Objects.requireNonNull(fareClass, "Fare class must not be null");

        RouteState routeState = RouteState.of(airports, origin, departureTime);

        while(!routeState.allReached()) {
            RouteNode currentNode = routeState.closestUnreached();
            if(currentNode.getAirport().equals(destination))
                return currentNode;
            routeStateHelper(routeState, currentNode, fareClass);
        }
        return null;
    }

    private void routeStateHelper(RouteState routeState, RouteNode currentNode, FareClass fareClass) {
        for(Flight flight : currentNode.availableFlights(fareClass)) {
            LocalTime flightArrival = flight.arrivalTime();
            if(flightArrival.compareTo(currentNode.departureTime().getTime()) < 0) {
                routeState.replaceNode(RouteNode.of(flight.origin(), new RouteTime(flightArrival), currentNode));
            }
        }
    }
}
