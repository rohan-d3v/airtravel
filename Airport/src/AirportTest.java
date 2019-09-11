import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AirportTest {


   Airport location1 = Airport.of("NYC", Duration.ofHours(10));
   Airport location2 = Airport.of("LHR", Duration.ofHours(13));

    @Test
    public void testEquals() {
        assertEquals(null, false);
        assertEquals("random object", false);
        assertEquals(location1.getCode(), "NYC");
        assertEquals(location2.getCode(), "LHR");
    }

    @Test
    public void testToString() {
        assertNotEquals(location1, location2);
        assertEquals(location1, location1);
    }

}