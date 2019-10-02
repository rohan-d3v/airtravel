package airport.cac226.rxr353;

import java.util.Objects;
import java.util.Set;
//import org.jetbrains.annotatio


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

        return new RouteNode(airport, routeArrivalTime, previous);
    }

    public static final RouteNode of(Flight flight, RouteNode previous){
        Objects.requireNonNull(flight);

        return new RouteNode(flight.destination(), new RouteTime(flight.arrivalTime()), previous);
    }

    public static final RouteNode of(Airport airport){
        Objects.requireNonNull(airport);

        return new RouteNode(airport, RouteTime.UNKNOWN, null);
    }

    public final Boolean isArrivalTimeKnow(){
        return arrivalTime.isKnown();
    }

    public final RouteTime departureTime(){
        return new RouteTime(arrivalTime.getTime().plus(airport.getConnectionTimeMin()));
    }

    public Set<Flight> availableFlights(FareClass fareClass) {
        Objects.requireNonNull(fareClass);
        return airport.availableFlights(arrivalTime.getTime(), fareClass);
    }

    @Override
    public int compareTo(RouteNode o) {
        if(o.getArrivalTime().compareTo(arrivalTime) != 0)
        {
            return arrivalTime.compareTo(o.getArrivalTime());
        }
        return airport.compareTo(o.getAirport());
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
