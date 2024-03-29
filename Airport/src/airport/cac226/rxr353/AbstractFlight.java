package airport.cac226.rxr353;

/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      AbstractFlight
 */

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public abstract class AbstractFlight implements Flight {
    /**
     * Airport origin method
     * @return origin
     */
    public final Airport origin() {
        return getLeg().origin();
    }

    /**
     * Gets airport destination
     * @return destination of flight
     */
    public final Airport destination() {
        return getLeg().destination();
    }

    /**
     * Localtime at start location
     * @return localTime (Like on a ticket)
     */
    public final LocalTime departureTime() {
        return getFlightSchedule().departureTime();
    }

    /**
     * Localtime at end location
     * @return localTime (Like on a ticket)
     */
    public final LocalTime arrivalTime() {
        return getFlightSchedule().arrivalTime();
    }

    /**
     * Check if a flight is a domestic/short flight
     * @param durationMax
     * @return
     */
    public final boolean isShort(Duration durationMax) {
        return getFlightSchedule().isShort(durationMax);
    }

    /**
     * Checks if the Flight has any available seats
     * @param fareclass
     * @return If seats are available
     */
    public final boolean hasSeats(FareClass fareclass) {
        Objects.requireNonNull(fareclass, "Fare class to check seats must not be null");
        return (seatsAvailable(fareclass).seats(fareclass.getSeatClass()) > 0);
    }
}
