/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      AbstractFlight
 */
import java.time.LocalTime;
import java.time.Duration;

public abstract class AbstractFlight implements Flight {
    public final Airport origin() {
        return getLeg().origin();
    }

    public final Airport destination() {
        return getLeg().destination();
    }

    public final LocalTime departureTime() {
        return getFlightSchedule().departureTime();
    }

    public final LocalTime arrivalTime() {
        return getFlightSchedule().arrivalTime();
    }

    public final boolean isShort(Duration durationMax) {
        return getFlightSchedule().isShort(durationMax);
    }
}
