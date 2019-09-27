package airport.cac226.rxr353;

import org.junit.Test;
import org.junit.Assert;
import java.time.LocalTime;
import java.time.Duration;

class RouteTimeTest {
    RouteTime unknown = RouteTime.UNKNOWN();
    RouteTime rt1 = new RouteTime(LocalTime.of(10, 4));
    RouteTime rt2 = new RouteTime(LocalTime.of(4, 2));
    Duration dur1 = Duration.ofMinutes(40);
    Duration dur2 =  Duration.ofMinutes(100);

    @Test
    void isKnown() {
        Assert.assertFalse(unknown.isKnown());
        Assert.assertTrue(rt1.isKnown());
        Assert.assertTrue(rt2.isKnown());
    }

    @Test
    void getTime() {
        Assert.assertEquals(rt1.getTime(), LocalTime.of(10, 4));
        Assert.assertEquals(rt2.getTime(), LocalTime.of(4, 2));
    }

    @Test
    void plus() {
        Assert.assertTrue(rt1.plus(dur1).compareTo(new RouteTime(LocalTime.of(10, 44))) == 0);
    }

    @Test
    void compareTo() {
        Assert.assertTrue(0 == unknown.compareTo(unknown));
        Assert.assertTrue(unknown.compareTo(rt1) > 0);
        Assert.assertTrue(rt1.compareTo(rt2) > 0);
        Assert.assertTrue(0 < rt2.compareTo(rt1));
    }
}