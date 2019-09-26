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
        new RouteNode()
        return RouteNode.of(origin, departureTime, fareClass);
    }
}
