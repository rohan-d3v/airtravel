package airport.cac226.rxr353;

import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.*;

public class AirportTest {

   Object nullObj = null;
   Object random = "Random";
   Airport location1 = Airport.of("NYC", Duration.ofHours(10));
   Airport location2 = Airport.of("LHR", Duration.ofHours(13));

    @Test
    public void testEquals() {
        assertFalse(location1.equals(nullObj));
        assertFalse(location2.equals(random));
        assertEquals(location1.getCode(), "NYC");
        assertEquals(location2.getCode(), "LHR");
    }

    @Test
    public void testToString() {
        assertNotEquals(location1, location2);
        assertEquals(location1, location1);
    }

}