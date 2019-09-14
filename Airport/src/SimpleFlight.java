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

    private SimpleFlight(String code, Leg leg, FlightSchedule flightSchedule) {
        this.code = code;
        this.leg = leg;
        this.flightSchedule = flightSchedule;
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
    public static final SimpleFlight of(String code, Leg leg, FlightSchedule flightSchedule) {
        Objects.requireNonNull(code, "Cannot initialize SimpleFlight without a code");
        Objects.requireNonNull(leg, "Cannot initialize SimpleFlight without a leg");
        Objects.requireNonNull(flightSchedule, "Cannot initialize SimpleFlight without a flight schedule");

        return new SimpleFlight(code, leg, flightSchedule);
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


}
