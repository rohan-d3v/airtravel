import java.time.LocalTime;
import java.util.NavigableMap;
import java.util.Set;
import java.util.HashSet;

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
        if(flights.containsValue(flight)) {
            throw new IllegalArgumentException("Flight already in flight group!");
        }
        if(flights.containsKey(flight.getFlightSchedule().arrivalTime())) {
            Set tempFlights = flights.get(flight.getFlightSchedule().arrivalTime());
            tempFlights.add(flight);
            flights.put(flight.getFlightSchedule().arrivalTime(), tempFlights);
            return true;
        }
        flights.put(flight.getFlightSchedule().arrivalTime(), new HashSet<Flight>(java.util.Arrays.asList(flight)));
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
