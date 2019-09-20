package airport.cac226.rxr353;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FareClassTest {

    Object random = "Random";

    FareClass testObj = FareClass.of(2, SeatClass.BUSINESS);
    FareClass testObj2 = FareClass.of(1, SeatClass.PREMIUM_ECONOMY);
    FareClass testObj3 = FareClass.of(2, SeatClass.BUSINESS);
    @Test
    public void testEquals() {
        assertFalse(testObj.equals(random));
        assertFalse(testObj.equals(testObj2));
        assertTrue(testObj.equals(testObj3));
    }
}