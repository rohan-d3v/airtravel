package airport.cac226.rxr353;

import java.time.LocalTime;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;

public final class RouteState {

    private Map<Airport, RouteNode> airportNode;

    private final NavigableSet<RouteNode> unreached;


    private RouteState(Set<Airport> airports, Airport origin, LocalTime departureTime) {

    }


}
