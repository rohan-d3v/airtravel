package airport.cac226.rxr353;

import org.junit.Test;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.EnumMap;

import static java.time.Duration.ZERO;

public class FlightGroupsTest {

FlightSchedule FS = FlightSchedule.of(LocalTime.now(ZoneId.of("UCT")), LocalTime.now(ZoneId.of("GMT")));
Airport start = Airport.of("UCT", ZERO);
Airport end = Airport.of("LHR", ZERO);
Leg l = Leg.of(start, end);
String FlightCode = "BA006";
EnumMap<SeatClass, Integer> seatEnum = new EnumMap<SeatClass, Integer>(SeatClass.class);
//SeatClass seatClass = SeatClass.BUSINESS;
//seatEnum.put(seatClass, 2);

SeatConfiguration seatConfig = SeatConfiguration.of(seatEnum);

SimpleFlight SF = SimpleFlight.of(FlightCode, l, FS, seatConfig);

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