
import java.time.LocalTime;
import java.time.Duration;

public class FlightSchedule {
    private final LocalTime arrivalTime;
    private final LocalTime departureTime;

    private FlightSchedule(LocalTime m_arrival, LocalTime m_departure)
    {
        arrivalTime = m_arrival;
        departureTime = m_departure;
    }

    public static FlightSchedule of(LocalTime m_arrival, LocalTime m_departure)
    {
        return new FlightSchedule(m_arrival, m_departure);
    }

    public boolean isShort(Duration durationMax)
    {
        return false;
    }
}
