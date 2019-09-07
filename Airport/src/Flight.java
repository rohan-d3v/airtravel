/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @interface:  Flight
 */
import java.time.LocalTime;

public interface Flight {
    public String getCode();
    public Leg getLeg();
    public Airport origin();
    public Airport destination();
    public FlightSchedule getFlightSchedule();
    public LocalTime departureTime();
    public LocalTime arrivalTime();
    public boolean isShort();
}
