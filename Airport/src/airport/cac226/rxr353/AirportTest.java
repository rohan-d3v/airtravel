package airport.cac226.rxr353;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.EnumMap;

import static java.time.Duration.ZERO;
import static org.junit.Assert.*;

public class AirportTest {

    Object nullObj = null;
    Object random = "Random";

    FlightSchedule FS = FlightSchedule.of(LocalTime.now(ZoneId.of("UCT")), LocalTime.now(ZoneId.of("GMT")));
    Airport location1 = Airport.of("NYC", Duration.ofHours(10));
    Airport location2 = Airport.of("LHR", Duration.ofHours(13));

    Airport start = Airport.of("NYC", ZERO);
    Airport end = Airport.of("LHR", ZERO);
    Leg l = Leg.of(start, end);

    String FlightCode = "BA006";

    EnumMap<SeatClass, Integer> seatEnum = new EnumMap<SeatClass, Integer>(SeatClass.class);

    SimpleFlight BA1= SimpleFlight.of(FlightCode, l, FS,  SeatConfiguration.of(seatEnum));

    @Test
    public void testEquals() {
        assertFalse(location2.equals(random));
        assertEquals(location1.getCode(), "NYC");
        assertEquals(location2.getCode(), "LHR");
    }

    @Test
    public void testToString() {
        assertNotEquals(location1, location2);
        assertEquals(location1, location1);
    }

    @Test
    public void addFlight() {
        assertTrue(location1.addFlight(BA1));
    }



    @Test
    public void removeFlight() {
        location1.addFlight(BA1);
        assertTrue(location1.removeFlight(BA1));
    }
}