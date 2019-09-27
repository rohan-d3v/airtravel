package airport.cac226.rxr353;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public final class RouteState {

    private Map<Airport, RouteNode> airportNode = new TreeMap<>();
    private final NavigableSet<RouteNode> unreached = new TreeSet<RouteNode>();
    private final List<RouteNode> reachedNodes = new ArrayList<RouteNode>();

    /**
     * ASSUMPTION: the "airports" set contains all relevant airports, i.e. any airports associated with any
     * "previous" RouteNode*/
    private RouteState(Set<Airport> airports, Airport origin, LocalTime departureTime) {
        RouteNode firstNode = RouteNode.of(origin, new RouteTime(departureTime), null);
        airportNode.put(origin, firstNode);
        reachedNodes.add(firstNode);

        for(Airport airport : airports.stream()
                                    .filter(airport -> !airport.equals(origin))
                                    .collect(Collectors.toSet())) {
            airportNode.put(airport, RouteNode.of(airport));
        }
        unreached.addAll(airportNode.values());
    }

    public static final RouteState of(Set<Airport> airports, Airport origin, LocalTime departureTime) {
        Objects.requireNonNull(airports);
        Objects.requireNonNull(origin);
        Objects.requireNonNull(departureTime);

        return new RouteState(airports, origin, departureTime);
    }

    // THERE IS SOMETHING WRONG WITH THIS BUT IT'S 2:04 AND I'M NOT SURE WHAT'S WRONG WITH IT
    private void updateUnreached() {
        List<RouteNode> previousList = unreached.stream()
                .filter(node -> node.getPrevious() != null)
                .collect(Collectors.toList());

        NavigableSet<RouteNode> nowReached = new TreeSet<RouteNode>();

        for(RouteNode node : previousList) {
            Airport previousAirport = node.getPrevious().getAirport();
            RouteNode targetNode = airportNode.get(previousAirport);
            if(reachedNodes.contains(targetNode)) {
                nowReached.add(node);
            }
        }

        unreached.removeAll(nowReached);
        reachedNodes.addAll(nowReached);
        if(!nowReached.isEmpty()) {
            updateUnreached();
        }
    }

    public void replaceNode(RouteNode routeNode) {
        //make this less complicated
        Objects.requireNonNull(routeNode, "Route node cannot be null");
        RouteNode toReplace = airportNode.replace(routeNode.getAirport(), routeNode);
        unreached.remove(toReplace);
        unreached.add(routeNode);
    }

    public boolean allReached() {
        return unreached.isEmpty();
    }

    public RouteNode closestUnreached() {
        if(allReached()) {
            throw new NoSuchElementException("All airports reached!");
        }
        RouteNode element = unreached.first();
        unreached.remove(element);
        return element;
    }

    public RouteNode airportNode(Airport airport) {
        Objects.requireNonNull(airport, "Airport cannot be null");
        return airportNode.get(airport);
    }
}
