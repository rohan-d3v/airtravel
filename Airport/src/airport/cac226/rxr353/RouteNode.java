package airport.cac226.rxr353;

import org.jetbrains.annotations.NotNull;

import java.time.LocalTime;
import java.util.Objects;

public final class RouteNode implements Comparable<RouteNode>{

    private final Airport airport;
    private final RouteTime arrivalTime;
    private final RouteNode previous;

    private RouteNode(Airport airport, RouteTime arrivalTime, RouteNode previous) {
        this.airport = airport;
        this.arrivalTime = arrivalTime;
        this.previous = previous;
    }

    public static final RouteNode of(Airport airport, RouteTime routeArrivalTime, RouteNode previous){
        Objects.requireNonNull(airport);
        Objects.requireNonNull(routeArrivalTime);
        //Objects.requireNonNull(previous); previous can be null

        return new RouteNode(airport, routeArrivalTime, previous);
    }

    public static final RouteNode of(Flight flight, RouteNode previous){
        Objects.requireNonNull(flight.destination());
        //Objects.requireNonNull(previous); previous can be null

        return new RouteNode(flight.destination(), null, previous);
    }

    public static final RouteNode of(Airport airport){
        Objects.requireNonNull(airport);

        return new RouteNode(airport, null, null);
    }

    public final Boolean isArrivalTimeKnow(){
        if(arrivalTime == null)
            return false;
        return true;
    }

    public final RouteTime departureTime(){
        LocalTime departureTime = arrivalTime.getTime().plus(airport.getConnectionTimeMin());
        return new RouteTime(departureTime);
    }

    @Override
    public int compareTo(@NotNull RouteNode o) {
        return 0;
    }


    /**
     * Standard Getter method, returns the Airport associated with this node
     * Auto generated
     * @return Airport
     */
    public Airport getAirport() {
        return airport;
    }

    /**
     * Standard Getter method, returns the arrival time
     * Auto generated
     * @return arrival time
     */
    public RouteTime getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Standard Getter method, returns the previous RouteNode
     * Auto generated
     * @return previous RouteNode
     */
    public RouteNode getPrevious() {
        return previous;
    }
}
