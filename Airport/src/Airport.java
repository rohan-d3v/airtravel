/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      Airport
 */
import java.time.Duration;

public final class Airport implements Comparable<Airport> {

    private final String code;
    private final Duration connectionTimeMin;

    private Airport(String new_code, Duration new_connectionTimeMin) {
        code = new_code;
        connectionTimeMin = new_connectionTimeMin;
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
        if(code == null || connectionTimeMin == null) {
            throw new NullPointerException("Airport must have a code and a minimum connection time");
        }

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
        if((obj == null) || !(obj instanceof Airport))
            return false;
        Airport otherAirport = (Airport) obj;
        return (otherAirport.getCode().equals(code) && otherAirport.getConnectionTimeMin().equals(connectionTimeMin));
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    public String getCode() {
        return code;
    }

    public Duration getConnectionTimeMin() {
        return connectionTimeMin;
    }

    @Override
    public int compareTo(Airport airport) {
        return code.compareTo(airport.getCode());
    }
}

