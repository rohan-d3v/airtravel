import java.util.Objects;
import java.util.function.BiFunction;

public final class FlightPolicy extends AbstractFlight{

    private final Flight flight;
    private final BiFunction<SeatConfiguration, FareClass, SeatConfiguration> policy;

    private FlightPolicy(Flight flight, BiFunction<SeatConfiguration, FareClass, SeatConfiguration> policy) {
        this.flight = flight;
        this.policy = policy;
    }

    public static final FlightPolicy of(Flight flight, BiFunction<SeatConfiguration, FareClass, SeatConfiguration> policy){
        Objects.requireNonNull(flight, "Flight cannot be empty");
        Objects.requireNonNull(policy, "Need a non null Flight Policy");

        return new FlightPolicy(flight, policy);
    }
    @Override
    public String getCode() {
        return flight.getCode();
    }

    @Override
    public Leg getLeg() {
        return flight.getLeg();
    }

    @Override
    public FlightSchedule getFlightSchedule() {
        return flight.getFlightSchedule();
    }
}
