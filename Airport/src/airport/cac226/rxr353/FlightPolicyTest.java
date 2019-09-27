package airport.cac226.rxr353;

import org.junit.Test;
import org.junit.Assert;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.EnumMap;

public class FlightPolicyTest {
    private Airport origin = Airport.of("CHI", Duration.ofHours(1));
    private Airport destination = Airport.of("CLE", Duration.ofMinutes(30));
    Leg leg = Leg.of(origin, destination);
    FlightSchedule flightSchedule = FlightSchedule.of(LocalTime.now(), LocalTime.now(ZoneId.of("GMT")));


    @Test
    public void seatsAvailable() {
        // ASK ABOUT THIS!!!
        EnumMap<SeatClass, Integer> seats = new EnumMap<SeatClass, Integer>(SeatClass.class);
        int seatCounter = 4;
        for(SeatClass seatClass : SeatClass.values()) {
            seats.put(seatClass, seatCounter);
            seatCounter++;
        }
        SeatConfiguration seatConfiguration = SeatConfiguration.of(seats);

        Flight simpleFlight = SimpleFlight.of("CLE", leg, flightSchedule, seatConfiguration);
        Flight fpStrict     = FlightPolicy.strict(simpleFlight);
        Flight fpReserve    = FlightPolicy.reserve(simpleFlight, 2);
        Flight fpRestricted = FlightPolicy.restrictedDuration(simpleFlight, Duration.ofHours(2));
        Flight fpLimited    = FlightPolicy.limited(simpleFlight);
        Flight fpPrice      = FlightPolicy.pricePolicy(simpleFlight, 100);
        Flight fpCombo      = FlightPolicy.limited(fpReserve);
        Flight fpUpgrade    = FlightPolicy.upgradeOnlyPolicy(simpleFlight);

        SeatConfiguration strictConfig      = fpStrict.seatsAvailable(FareClass.of(6, SeatClass.PREMIUM_ECONOMY));
        SeatConfiguration reserveConfig     = fpReserve.seatsAvailable(FareClass.of(6, SeatClass.PREMIUM_ECONOMY));
        SeatConfiguration restrictedConfig  = fpRestricted.seatsAvailable(FareClass.of(6, SeatClass.PREMIUM_ECONOMY));
        SeatConfiguration limitedConfig     = fpLimited.seatsAvailable(FareClass.of(6, SeatClass.PREMIUM_ECONOMY));
        SeatConfiguration fpPriceUnder      = fpPrice.seatsAvailable(FareClass.of(6, SeatClass.PREMIUM_ECONOMY));
        SeatConfiguration fpPriceOver       = fpPrice.seatsAvailable(FareClass.of(600, SeatClass.PREMIUM_ECONOMY));
        SeatConfiguration fpComboConfig     = fpCombo.seatsAvailable(FareClass.of(600, SeatClass.PREMIUM_ECONOMY));
        SeatConfiguration upgradeConfig     = fpUpgrade.seatsAvailable(FareClass.of(600, SeatClass.PREMIUM_ECONOMY));

        Assert.assertEquals(strictConfig.seats(SeatClass.PREMIUM_ECONOMY), 5);
        Assert.assertEquals(reserveConfig.seats(SeatClass.PREMIUM_ECONOMY), 3);
        Assert.assertEquals(restrictedConfig.seats(SeatClass.PREMIUM_ECONOMY), 5);
        Assert.assertEquals(limitedConfig.seats(SeatClass.PREMIUM_ECONOMY), (4 + 5));
        Assert.assertEquals(fpPriceUnder.seats(SeatClass.PREMIUM_ECONOMY), 5);
        Assert.assertEquals(fpPriceOver.seats(SeatClass.PREMIUM_ECONOMY), (4 + 5 + 6));
        Assert.assertEquals(fpComboConfig.seats(SeatClass.PREMIUM_ECONOMY), (2 + 3));
        Assert.assertEquals(upgradeConfig.seats(SeatClass.PREMIUM_ECONOMY), 4);
    }

    @Test
    public void strict() {
    }

    @Test
    public void restrictedDuration() {
    }

    @Test
    public void reserve() {
    }

    @Test
    public void limited() {
    }

    @Test
    public void pricePolicy() {
    }

    @Test
    public void upgradeOnlyPolicy() {
    }
}