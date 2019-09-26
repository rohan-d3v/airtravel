package airport.cac226.rxr353;

import java.util.Map;
import java.util.NavigableSet;

public final class RouteState {

    private Map<Airport, RouteNode> airportNode;

    private final NavigableSet<RouteNode> unreached;


    private RouteState(Map<Airport, RouteNode> airportNode, NavigableSet<RouteNode> unreached) {
        this.airportNode = airportNode;
        this.unreached = unreached;
    }
}
