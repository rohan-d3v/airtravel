package airport.cac226.rxr353;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;

import static org.junit.Assert.assertTrue;


public class FlightScheduleTest {


    Duration testDuration = Duration.ofHours(10);

    FlightSchedule FS = FlightSchedule.of(LocalTime.now(), LocalTime.now(ZoneId.of("GMT")));

    @Test
    public void testIsShort() {
        assertTrue(FS.isShort(Duration.ZERO));
    }

}