package airport.cac226.rxr353;

/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      Airport
 */

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class Airport implements Comparable<Airport> {

    private final String code;
    private final Duration connectionTimeMin;
    private final FlightGroups outFlights;

    public Set<Flight> availableFlights(LocalTime departureTime, FareClass fareClass) {
        Objects.requireNonNull(departureTime);
        Objects.requireNonNull(fareClass);

        return outFlights.flightsAtOrAfter(departureTime).stream()
                .filter(flight -> flight.hasSeats(fareClass))
                .collect(Collectors.toSet());
    }

    private Airport(String new_code, Duration new_connectionTimeMin) {
        code = new_code;
        connectionTimeMin = new_connectionTimeMin;
        outFlights = FlightGroups.of(this);
    }

    /**
     * Builder method since the Constructor is private
     * Checks if the values are valid before accessing the constructor
     * Returns new instance of Airport
     * @param code Airport Code
     * @param connectionTimeMin Minimum Connection rime required
     * @return Airport Object
     */
    public static final Airport of(String code, Duration connectionTimeMin) {
        Objects.requireNonNull(code, "Airport must have a valid code");
        Objects.requireNonNull(connectionTimeMin, "Airport must have a minimum connection time");

        return new Airport(code, connectionTimeMin);
    }


    /**
     * Checks if the Object is actually a valid type airport
     * Makes sure it's not null and is an instance of airport
     * @param obj Any time object (technically) but preferably airport
     * @return boolean whether the method is actually valid
     */
    @Override
    public boolean equals(Object obj) {
        Objects.requireNonNull(obj);
        if(!(obj instanceof Airport))
            return false;
        Airport otherAirport = (Airport) obj;
        return otherAirport.getCode().equals(code);
    }

    /**
     * Standard Getter method, returns the string version of the code
     * Auto generated
     * @return Airport Code
     */
    @Override
    public String toString() {
        return code;
    }

    /**
     * Standard hash method, returns the string version of the code
     * Auto generated
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return code.hashCode();
    }

    /**
     * Standard Getter method, returns the string version of the code
     * Auto generated
     * @return Airline Code
     */
    public String getCode() {
        return code;
    }

    /**
     * Standard Getter method, returns the string version of the code
     * Auto generated
     * @return Connection Time (duration)
     */
    public Duration getConnectionTimeMin() {
        return connectionTimeMin;
    }

    /**
     * Standard compare method, returns the string version of the code
     * Auto generated
     * @return Airport Code
     */
    @Override
    public int compareTo(Airport airport) {
        Objects.requireNonNull(airport, "Airport to compare cannot be null");
        return code.compareTo(airport.getCode());
    }

    /**
     * Create Flights and add to the airport departures
     * @param flight
     * @return
     */
    public final boolean addFlight(Flight flight) {
        return outFlights.add(flight);
    }

    /**
     * Removes flights from departures
     * @param flight
     * @return
     */
    public final boolean removeFlight(Flight flight) {
        return outFlights.remove(flight);
    }
}

