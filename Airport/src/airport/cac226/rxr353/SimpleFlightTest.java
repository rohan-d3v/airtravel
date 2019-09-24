package airport.cac226.rxr353;

import org.junit.Test;
import org.junit.Assert;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.EnumMap;

public class SimpleFlightTest {
    private Airport origin = Airport.of("CHI", Duration.ofHours(1));
    private Airport destination = Airport.of("CLE", Duration.ofMinutes(30));
    Leg leg = Leg.of(origin, destination);
    FlightSchedule flightSchedule = FlightSchedule.of(LocalTime.now(), LocalTime.now(ZoneId.of("GMT")));

    @Test
    public void seatsAvailable() {
        //set up things
        EnumMap<SeatClass, Integer> seats = new EnumMap<SeatClass, Integer>(SeatClass.class);
        int seatCounter = 4;
        for(SeatClass seatClass : SeatClass.values()) {
            seats.put(seatClass, seatCounter);
            seatCounter++;
        }
        SeatConfiguration seatConfiguration = SeatConfiguration.of(seats);

        SimpleFlight flight1 = SimpleFlight.of("CLE", leg, flightSchedule, seatConfiguration);
        SeatConfiguration fareClassSeatConfiguration = flight1.seatsAvailable(FareClass.of(40, SeatClass.BUSINESS));

        for(SeatClass seatClass : SeatClass.values()) {
            Assert.assertEquals(seatConfiguration.seats(seatClass), fareClassSeatConfiguration.seats(seatClass));
        }
    }
}