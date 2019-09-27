package airport.cac226.rxr353;

import org.junit.Test;
import org.junit.Assert;
import java.time.LocalTime;
import java.time.Duration;

public class RouteTimeTest {
    private RouteTime unknown = RouteTime.UNKNOWN();
    private RouteTime rt1 = new RouteTime(LocalTime.of(10, 4));
    private RouteTime rt2 = new RouteTime(LocalTime.of(4, 2));
    private Duration dur1 = Duration.ofMinutes(40);
    private Duration dur2 =  Duration.ofMinutes(100);

    @Test
    public void isKnown() {
        Assert.assertFalse(unknown.isKnown());
        Assert.assertTrue(rt1.isKnown());
        Assert.assertTrue(rt2.isKnown());
    }

    @Test
    public void getTime() {
        Assert.assertEquals(rt1.getTime(), LocalTime.of(10, 4));
        Assert.assertEquals(rt2.getTime(), LocalTime.of(4, 2));
    }

    @Test
    public void plus() {
        Assert.assertTrue(rt1.plus(dur1).compareTo(new RouteTime(LocalTime.of(10, 44))) == 0);
    }

    @Test
    public void compareTo() {
        Assert.assertTrue(0 == unknown.compareTo(unknown));
        Assert.assertEquals(true,unknown.compareTo(rt1) < 0);
        Assert.assertEquals(true,rt2.compareTo(rt1) < 0);
        Assert.assertTrue(rt1.compareTo(rt2) > 0);
    }
}