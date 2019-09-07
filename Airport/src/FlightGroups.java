import java.time.LocalTime;
import java.util.NavigableMap;
import java.util.Set;

/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      FlightGroups
 */
public final class FlightGroups {

    private final Airport origin;


    private final NavigableMap<LocalTime, Set<Flight>> flights;

    public FlightGroups(Airport origin, NavigableMap<LocalTime, Set<Flight>> flights) {
        this.origin = origin;
        this.flights = flights;
    }


    public final boolean add(Flight flight){
        return true;
    }

    public final boolean remove(Flight flight){
        return false;
    }
/*
    public final Set<Flight> flightsAtOrAfter(LocalTime departureTime){
        return
    }
    */

}
