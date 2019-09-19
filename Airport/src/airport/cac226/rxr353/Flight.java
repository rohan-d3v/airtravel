package airport.cac226.rxr353;

/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @interface:  Flight
 */

import java.time.Duration;
import java.time.LocalTime;

/**
 * Flight Interface
 */
public interface Flight {
    String getCode();
    Leg getLeg();
    Airport origin();
    Airport destination();
    FlightSchedule getFlightSchedule();
    LocalTime departureTime();
    LocalTime arrivalTime();
    boolean isShort(Duration durationMax);
    SeatConfiguration seatsAvailable(FareClass fareClass);
    boolean hasSeats(FareClass fareClass);
}
