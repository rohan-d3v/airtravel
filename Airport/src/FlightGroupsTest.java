import org.junit.Test;

import java.time.LocalTime;
import java.time.ZoneId;

import static java.time.Duration.ZERO;

public class FlightGroupsTest {

FlightSchedule FS = FlightSchedule.of(LocalTime.now(ZoneId.of("EST")), LocalTime.now(ZoneId.of("GMT")));
Airport start = Airport.of("NYC", ZERO);
Airport end = Airport.of("LHR", ZERO);
Leg l = Leg.of(start, end);
String FlightCode = "BA006";
SimpleFlight SF = SimpleFlight.of(FlightCode, l, FS);

    @org.junit.Test
    public void add() {

    }

    @org.junit.Test
    public void remove() {

    }



    @Test
    public void flightsAtOrAfter() {

    }
}