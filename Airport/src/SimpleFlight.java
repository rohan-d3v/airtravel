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

    private SimpleFlight(String code, Leg leg, FlightSchedule flightSchedule) {
        this.code = code;
        this.leg = leg;
        this.flightSchedule = flightSchedule;
    }

    public static final SimpleFlight of(String code, Leg leg, FlightSchedule flightSchedule) {
        if(code == null || leg == null || flightSchedule == null) {
            throw new IllegalArgumentException("Cannot initialize SimpleFlight without a code, leg, and a flight schedule");
        }
        return new SimpleFlight(code, leg, flightSchedule);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Leg getLeg() {
        return leg;
    }
    @Override
    public FlightSchedule getFlightSchedule() {
        return flightSchedule;
    }


}
