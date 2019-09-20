package airport.cac226.rxr353;

/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      SimpleFlight
 */

import java.util.Objects;

public final class SimpleFlight extends AbstractFlight{

    private final String code;
    private final Leg leg;
    private final FlightSchedule flightSchedule;
    private final SeatConfiguration seatsAvailable;

    private SimpleFlight(String code, Leg leg, FlightSchedule flightSchedule, SeatConfiguration seatsAvailable) {
        this.code = code;
        this.leg = leg;
        this.flightSchedule = flightSchedule;
        this.seatsAvailable = seatsAvailable;
        origin().addFlight(this);
    }

    /**
     * Builder method since the Constructor is private
     * Requires Leg, Schedule and Airplane code to be not null
     * @param code
     * @param leg
     * @param flightSchedule
     * @return Object type simple Flight
     */
    public static final SimpleFlight of(String code, Leg leg, FlightSchedule flightSchedule, SeatConfiguration seatsAvailable) {
        Objects.requireNonNull(code, "Cannot initialize SimpleFlight without a code");
        Objects.requireNonNull(leg, "Cannot initialize SimpleFlight without a leg");
        Objects.requireNonNull(flightSchedule, "Cannot initialize SimpleFlight without a flight schedule");
        Objects.requireNonNull(seatsAvailable, "Cannot initialize SimpleFlight without a seat configuration");

        return new SimpleFlight(code, leg, flightSchedule, seatsAvailable);
    }

    /**
     * Standard Getter method, returns the Airplane Code
     * Auto Generated
     * @return Airplane Code
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * Standard Getter method, returns the Leg of the Journey
     * Auto Generated
     * @return Leg
     */
    @Override
    public Leg getLeg() {
        return leg;
    }

    /**
     * Standard Getter method, returns the Flight Schedule
     * Auto Generated
     * @return FlightSchedule
     */
    @Override
    public FlightSchedule getFlightSchedule() {
        return flightSchedule;
    }

    /**
     * Cannot make private because of pre existing method clash from flight interface
     * Has been initialized in build and of methods
     * @param fareclass
     * @return Object type seat Configuration
     */
    public final SeatConfiguration seatsAvailable(FareClass fareclass) {
        Objects.requireNonNull(fareclass, "Please enter a valid fareclass");
        return seatsAvailable;
    }
}
