/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      FlightSchedule
 */
import java.time.LocalTime;
import java.time.Duration;

public final class FlightSchedule {
    private final LocalTime arrivalTime;
    private final LocalTime departureTime;
    private final Duration duration;

    private FlightSchedule(LocalTime m_arrival, LocalTime m_departure)
    {
        arrivalTime = m_arrival;
        departureTime = m_departure;
        duration = Duration.between(departureTime, arrivalTime);
    }

    public static FlightSchedule of(LocalTime m_arrival, LocalTime m_departure) {
        if(m_arrival == null || m_departure == null) {
            throw new NullPointerException("Departure/arrival time cannot be null");
        }
        return new FlightSchedule(m_arrival, m_departure);
    }

    public boolean isShort(Duration durationMax) {
        if(durationMax == null) {
            throw new NullPointerException("Duration cannot be null");
        }
        return durationMax.compareTo(duration) <= 0;
    }

    public LocalTime departureTime()
    {
        return departureTime;
    }

    public LocalTime arrivalTime()
    {
        return arrivalTime;
    }
}
