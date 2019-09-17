package airport.cac226.rxr353;

/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      SimpleFlight
 */


import java.util.Objects;
import java.util.function.BiFunction;

public final class FlightPolicy extends AbstractFlight {

    private final Flight flight;
    private final BiFunction<SeatConfiguration, FareClass, SeatConfiguration> policy;

    private FlightPolicy(Flight flight, BiFunction<SeatConfiguration, FareClass, SeatConfiguration> policy) {
        this.flight = flight;
        this.policy = policy;
    }

    /**
     * Builder method since the Constructor is private
     * Requires Flight and policy to not be null
     * @param flight
     * @param policy
     * @return new Flight Policy Object
     */
    public static final FlightPolicy of(Flight flight, BiFunction<SeatConfiguration, FareClass, SeatConfiguration> policy){
        Objects.requireNonNull(flight, "Flight cannot be empty");
        Objects.requireNonNull(policy, "Need a non null Flight Policy");

        return new FlightPolicy(flight, policy);
    }

    /**
     * Auto-generated getter method
     * @return Flight Code
     */
    @Override
    public String getCode() {
        return flight.getCode();
    }

    /**
     * Auto-generated getter method
     * @return Flight leg
     */
    @Override
    public Leg getLeg() {
        return flight.getLeg();
    }

    /**
     * Auto-generated getter method
     * @return Flight Schedule
     */
    @Override
    public FlightSchedule getFlightSchedule() {
        return flight.getFlightSchedule();
    }

    public SeatConfiguration seatsAvailable(FareClass fareclass) {
        Objects.requireNonNull(fareclass, "Fareclass cannot be null");
        //SeatConfiguration seatConfig = policy.apply()
        return null;
    }
}
