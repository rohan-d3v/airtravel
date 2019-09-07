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

    private final NavigableMap<LocalTime, Set<Flight>> flights;

    public FlightGroups(NavigableMap<LocalTime, Set<Flight>> flights) {
        this.flights = flights;
    }


}
