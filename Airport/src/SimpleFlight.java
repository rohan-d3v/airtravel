import java.time.Duration;
import java.time.LocalTime;

/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      SimpleFlight
 */
public final class SimpleFlight extends AbstractFlight{

    private final String code;
    private final Leg leg;
    private final FlightSchedule flightSchedule;

    public SimpleFlight(String code, Leg leg, FlightSchedule flightSchedule) {
        this.code = code;
        this.leg = leg;
        this.flightSchedule = flightSchedule;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Leg getLeg() {
        return null;
    }
    @Override
    public FlightSchedule getFlightSchedule() {
        return null;
    }


}
