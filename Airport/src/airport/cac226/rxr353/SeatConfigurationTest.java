package airport.cac226.rxr353;

import org.junit.Test;

import java.util.EnumMap;

import static airport.cac226.rxr353.SeatClass.BUSINESS;
import static airport.cac226.rxr353.SeatClass.ECONOMY;
import static org.junit.Assert.*;

public class SeatConfigurationTest {

    EnumMap<SeatClass, Integer> seatEnum = new EnumMap<SeatClass, Integer>(SeatClass.class);
    public void setSeatEnum(){
        seatEnum.put(BUSINESS, 2);
    }

    SeatConfiguration initSeatConfig = SeatConfiguration.of(seatEnum);
    SeatConfiguration testSeatConfig = SeatConfiguration.of(initSeatConfig);


    @Test
    public void seats() {
        seatEnum.put(BUSINESS, 2);
        SeatConfiguration initSeatConfig = SeatConfiguration.of(seatEnum);
        SeatConfiguration testSeatConfig = SeatConfiguration.of(initSeatConfig);
        assertEquals(2, testSeatConfig.seats(BUSINESS));
        assertNotEquals(1, testSeatConfig.seats(BUSINESS));
    }

    @Test
    public void setSeats() {
        assertEquals( 15, testSeatConfig.setSeats(ECONOMY, 15));
    }

    @Test
    public void hasSeats() {
        assertTrue(testSeatConfig.hasSeats());
    }
}