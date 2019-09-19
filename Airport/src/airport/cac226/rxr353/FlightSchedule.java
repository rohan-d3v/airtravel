package airport.cac226.rxr353;

/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      FlightSchedule
 */

import java.time.LocalTime;
import java.time.Duration;
import java.util.Objects;

public final class FlightSchedule {

    private final LocalTime arrivalTime;
    private final LocalTime departureTime;
    private final Duration duration;

    private FlightSchedule(LocalTime m_arrival, LocalTime m_departure) {
        arrivalTime = m_arrival;
        departureTime = m_departure;
        duration = Duration.between(departureTime, arrivalTime);
    }

    /**
     * Builder method since the Constructor is private
     * Requires Arrival and Departure time to be non null
     * @param m_arrival
     * @param m_departure
     * @return FlightSchedule Object
     */
    public static FlightSchedule of(LocalTime m_arrival, LocalTime m_departure) {
        Objects.requireNonNull(m_arrival, "Arrival cannot be null");
        Objects.requireNonNull(m_departure, "Departure cannot be null");

        return new FlightSchedule(m_arrival, m_departure);
    }

    /**
     * Compares and checks that the duration is greater than zero
     * Max time for the Flight
     * Makes sure duration is not null
     * @param durationMax
     * @return Boolean whether duration is greater or lesser than 0
     */
    public boolean isShort(Duration durationMax) {
        Objects.requireNonNull(durationMax, "Duration cannot be null");

        return durationMax.compareTo(duration) <= 0;
    }

    /**
     * Standard Getter method, returns the Departure Time
     * Auto Generated
     * @return Departure Time
     */
    public LocalTime departureTime() {
        return departureTime;
    }

    /**
     * Standard Getter method, returns the Departure Time
     * Auto Generated
     * @return Arrival Time
     */
    public LocalTime arrivalTime() {
        return arrivalTime;
    }
}
