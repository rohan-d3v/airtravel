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

    // ASK ELLIS: double check it's fine if our complexity is 4;
    public final RouteNode route(Airport origin, Airport destination, LocalTime departureTime, FareClass fareClass){
        Objects.requireNonNull(origin, "Airport origin must not be null");
        Objects.requireNonNull(destination, "Airport destination must not be null");
        Objects.requireNonNull(departureTime, "Departure time must not be null");
        Objects.requireNonNull(fareClass, "Fare class must not be null");

        RouteState routeState = RouteState.of(airports, origin, departureTime);

        // ASK ELLIS: this has a complexity of 4: is there a way to lower it/is this okay?

        while(!routeState.allReached()) {
            RouteNode currentNode = routeState.closestUnreached();
            if(currentNode.getAirport().equals(destination)) return currentNode;
            for(Flight flight : currentNode.availableFlights(fareClass)) {
                //ASK ELLIS: IS THIS NECESSARY?
                LocalTime flightArrival = flight.arrivalTime();
                if(flightArrival.compareTo(currentNode.departureTime().getTime()) < 0) {
                    routeState.replaceNode(RouteNode.of(flight.origin(), new RouteTime(flightArrival), null));
                }
            }
        }
        return null;
    }
}
