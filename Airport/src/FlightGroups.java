import java.time.LocalTime;
import java.util.*;

/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      FlightGroups
 */
public final class FlightGroups {

    private final Airport origin;
    private final NavigableMap<LocalTime, Set<Flight>> flights;

    private FlightGroups(Airport origin) {
        this.origin = origin;
        this.flights = new TreeMap<LocalTime, Set<Flight>>();
    }

    public static final FlightGroups of(Airport origin)
    {
        if(origin == null)
        {
            throw new NullPointerException("Origin airport cannot be null!");
        }
        return new FlightGroups(origin);
    }

    public final Airport getOrigin()
    {
        return origin;
    }

    /**
     * Add Flight to the groups of flight, cj
     * @param flight
     * @return
     */
    public final boolean add(Flight flight){
        if(flights.containsValue(flight)) {
            throw new IllegalArgumentException("Flight already in flight group!");
        }
        if(flights.containsKey(flight.getFlightSchedule().departureTime())) {
            Set tempFlights = flights.get(flight.getFlightSchedule().departureTime());
            return tempFlights.add(flight);
        }
        flights.put(flight.getFlightSchedule().departureTime(), new HashSet<Flight>(java.util.Arrays.asList(flight)));
        return true;
    }

    public final boolean remove(Flight flight){
        if(flight == null)
        {
            throw new NullPointerException("Flight cannot be null!");
        }
        return flights.remove(flight.getFlightSchedule().departureTime()) != null;
    }

    public final Set<Flight> flightsAtOrAfter(LocalTime departureTime){
        SortedMap<LocalTime, Set<Flight>> atOrAfter = flights.tailMap(departureTime);
        Set<Flight> result = new HashSet<Flight>();
        for(Set<Flight> flightsList : atOrAfter.values())
        {
            result.addAll(flightsList);
        }
        return result;
    }
}
