package airport.cac226.rxr353;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RouteNodeTest {


        Airport airport = Airport.of("NYC", Duration.ofHours(10));
        RouteTime routeTime = new RouteTime(LocalTime.now(ZoneId.of("EST")));
        RouteNode previous = RouteNode.of(Airport.of("LAX", Duration.ofHours(10)));;
        RouteNode routeNode1 = RouteNode.of(airport, routeTime, previous);
        RouteNode routeNode2 = RouteNode.of(Airport.of("ORD", Duration.ofHours(5)));


    @Test
    public void isArrivalTimeKnow() {
        assertTrue(routeNode1.isArrivalTimeKnow());
        assertFalse(routeNode2.isArrivalTimeKnow());
    }

    @Test
    public void departureTime() {
    }

    @Test
    public void availableFlights() {
    }

    @Test
    public void compareTo() {
    }
}