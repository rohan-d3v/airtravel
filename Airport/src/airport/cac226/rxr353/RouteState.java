package airport.cac226.rxr353;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public final class RouteState {

    private Map<Airport, RouteNode> airportNode;
    private final NavigableSet<RouteNode> unreached = new TreeSet<RouteNode>();
    private final List<RouteNode> reachedNodes = new ArrayList<RouteNode>();


    private RouteState(Set<Airport> airports, Airport origin, LocalTime departureTime) {
        airportNode = new TreeMap<>();
        airportNode.put(origin, RouteNode.of(origin, new RouteTime(departureTime), null));
        for(Airport airport : airports) {
            airportNode.put(airport, RouteNode.of(airport));
        }
        unreached.addAll(airportNode.values());
    }

    // updates unreached
    // returns "true" if changed something
    private void updateUnreached() {
        List<RouteNode> previousList = unreached.stream()
                .filter(node -> node.getPrevious() != null)
                .collect(Collectors.toList());
        NavigableSet<RouteNode> nowReached = new TreeSet<RouteNode>;

        for(RouteNode node : previousList) {
            if(reachedNodes.contains(node.getPrevious())) {
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
        Objects.requireNonNull(routeNode, "Route node cannot be null");
        RouteNode toReplace = airportNode.values().stream()
                .filter(node -> node.getAirport().equals(routeNode.getAirport()))
                .findAny()
                .orElse(null);
        if(toReplace == null) {
            throw new IllegalArgumentException("Replacement node must have corresponding node in set of same airport");
        }
        unreached.remove(toReplace);
        airportNode.replace(routeNode.getAirport(), routeNode);
        updateUnreached();
    }

    public boolean allReached() {
        return unreached.isEmpty();
    }

    public RouteNode closestUnreached() {
        if(allReached()) {
            throw new NoSuchElementException("All airports reached!");
        }
        return airportNode.get(unreached.stream()
                                        .min(Comparator.comparing(RouteNode::getArrivalTime))
                                        .get());
    }

    public RouteNode airportNode(Airport airport) {
        Objects.requireNonNull(airport, "Airport cannot be null");
        return airportNode.get(airport);
    }
}
