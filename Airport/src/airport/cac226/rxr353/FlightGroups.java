package airport.cac226.rxr353;

/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      FlightGroups
 */


import java.time.LocalTime;
import java.util.*;

public final class FlightGroups {

    private final Airport origin;
    private final NavigableMap<LocalTime, Set<Flight>> flights;

    private FlightGroups(Airport origin) {
        this.origin = origin;
        this.flights = new TreeMap<LocalTime, Set<Flight>>();
    }

    /**
     * Builder method since the Constructor is private
     * Checks if the value is non null before creating object
     * @param origin
     * @return Ariport object
     */
    public static final FlightGroups of(Airport origin) {
        Objects.requireNonNull(origin, "Origin airport can't be null!");

        return new FlightGroups(origin);
    }

    /**
     * Standard Getter method, returns Origin airport code
     * Auto generated
     * @return Airport Code
     */
    public final Airport getOrigin() {
        return origin;
    }


    /**
     * Checks if FlightGroups contains Flights
     * Add Flights to temporary set
     * Puts flight Details in Flight set
     * @param flight
     * @return boolean if in set
     */
    public final boolean add(Flight flight){
        Objects.requireNonNull(flight, "Flight to be added cannot be null!");
        if(!flight.origin().getCode().equals(origin.getCode())) {
            throw new IllegalArgumentException("Airport to be added must have same origin airport as flight group");
        }

        if(flights.containsValue(flight)) {
            return false;
        }

        //Checks if Flights has the correct details then adds to temp set
        if(flights.containsKey(flight.getFlightSchedule().departureTime())) {
            Set tempFlights = flights.get(flight.getFlightSchedule().departureTime());
            return tempFlights.add(flight);
        }
        flights.put(flight.getFlightSchedule().departureTime(), new HashSet<Flight>(java.util.Arrays.asList(flight)));
        return true;
    }

    /**
     * Removes Flight from Flights set
     * Uses FlightSchedule to check Flights before removing
     * @param flight
     * @return
     */
    public final boolean remove(Flight flight){
        Objects.requireNonNull(flight, "Flight cannot be null!");
        if(origin.equals(flight.origin()) && flights.get(flight.getFlightSchedule().departureTime()) != null) {
            return flights.get(flight.getFlightSchedule().departureTime()).remove(flight);
        }
        return false;
    }

    /**
     * Checks if Flight exists at stated departure time
     * Runs through the whole set checking whether the rest of the flights are at or after departure time
     * @param departureTime
     * @return flights at or after departure time
     */
    public final Set<Flight> flightsAtOrAfter(LocalTime departureTime){
        Objects.requireNonNull(departureTime, "Departure time cannot be null");
        SortedMap<LocalTime, Set<Flight>> atOrAfter = flights.tailMap(departureTime);
        Set<Flight> result = new HashSet<Flight>();
        for(Set<Flight> flightsList : atOrAfter.values()) {
            result.addAll(flightsList);
        }
        return result;
    }
}
