package airport.cac226.rxr353;

import org.junit.Test;
import java.time.Duration;

public class SimpleFlightTest {
    private Airport origin = Airport.of("CHI", Duration.ofHours(1));
    private Airport destination = Airport.of("CLE", Duration.ofMinutes(30));
    Leg leg = Leg.of(origin, destination);

    @Test
    public void getCode() {
    }

    @Test
    public void getLeg() {
    }

    @Test
    public void getFlightSchedule() {
    }

    @Test
    public void seatsAvailable() {

    }
}