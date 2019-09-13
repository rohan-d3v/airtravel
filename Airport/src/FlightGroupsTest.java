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
        FlightGroups flights = FlightGroups.of(start);
        assert(flights.add(SF));
        assert(!flights.add(SF));
    }

    @org.junit.Test
    public void remove() {
        FlightGroups flights = FlightGroups.of(start);
        assert(flights.add(SF));
        assert(flights.remove(SF));
        assert(!flights.remove(SF));
    }

    @Test
    public void flightsAtOrAfter() {
        FlightGroups flights = FlightGroups.of(start);
        assert(flights.add(SF));
        assert(flights.flightsAtOrAfter(SF.departureTime()).contains(SF));
    }
}