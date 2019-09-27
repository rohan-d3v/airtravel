package airport.cac226.rxr353;


import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class RouteStateTest {

    RouteState state;

    LocalTime startTime             = LocalTime.now();
    LocalTime cle_to_ord_departure  = startTime.plusMinutes(30);
    LocalTime cle_to_ord_arrival    = cle_to_ord_departure.plusMinutes(110);
    LocalTime ord_to_fld_departure  = cle_to_ord_arrival.plusMinutes(110);
    LocalTime ord_to_fld_arrival    = ord_to_fld_departure.plusMinutes(160);
    LocalTime max_arrival           = ord_to_fld_arrival.plusMinutes(20);

    Airport cle = Airport.of("CLE", Duration.ofMinutes(20));
    Airport ord = Airport.of("ORD", Duration.ofMinutes(100));
    Airport fld = Airport.of("FLD", Duration.ofMinutes(30));
    Set<Airport> airports = new TreeSet<Airport>();

    @Before
    public void setUp() {
        EnumMap<SeatClass, Integer> seatEnum = new EnumMap<SeatClass, Integer>(SeatClass.class);
        seatEnum.put(SeatClass.BUSINESS, 2);
        seatEnum.put(SeatClass.PREMIUM_ECONOMY, 3);
        SeatConfiguration seatConfig = SeatConfiguration.of(seatEnum);

        FlightSchedule cle_ord_schedule = FlightSchedule.of(cle_to_ord_departure, cle_to_ord_arrival);
        FlightSchedule ord_fld_schedule = FlightSchedule.of(ord_to_fld_departure, ord_to_fld_arrival);

        Leg cle_ord_leg = Leg.of(cle, ord);
        Leg ord_fld_leg = Leg.of(ord, fld);

        //String code, Leg leg, FlightSchedule flightSchedule, SeatConfiguration seatsAvailable
        cle.addFlight(SimpleFlight.of("ABCD", cle_ord_leg, cle_ord_schedule, seatConfig));
        ord.addFlight(SimpleFlight.of("EFGH", ord_fld_leg, ord_fld_schedule, seatConfig));

        airports.add(cle);
        airports.add(ord);
        airports.add(fld);

        //
    }

    @Test
    public void replaceNode() {
    }

    @Test
    public void allReached() {
        setUp();

        RouteState state = RouteState.of(airports, cle, max_arrival);

        //Assert.assertFalse(state.allReached());
    }

    @Test
    public void closestUnreached() {
    }

    @Test
    public void airportNode() {
    }
}