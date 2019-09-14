import java.util.EnumMap;

public final class SeatConfiguration {
    private final EnumMap<SeatClass, Integer> seats;

    private SeatConfiguration(EnumMap<SeatClass, Integer> seats) {
        this.seats = seats;
    }

}
