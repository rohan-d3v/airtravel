/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      SeatConfiguration
 */


import java.util.*;
import java.util.EnumMap;

public final class SeatConfiguration {
    private final EnumMap<SeatClass, Integer> seats;

    private SeatConfiguration(EnumMap<SeatClass, Integer> seats) {
        this.seats = seats.clone();
    }

    public int seats(SeatClass seatClass) {
        Objects.requireNonNull(seatClass, "Seat class cannot be null");
        if(seats.containsKey(seatClass)) {
            return seats.get(seatClass);
        }
        return 0;
    }

    public int setSeats(SeatClass seatClass, int seats) {
        Objects.requireNonNull(seatClass, "Seat Class cannot be null");
        int initialSeatsAvailable = seats(seatClass);
        this.seats.put(seatClass, seats);
        return initialSeatsAvailable;
    }

    public boolean hasSeats() {
        Collection<Integer> seatsAvailable = seats.values();
        return seatsAvailable.stream().anyMatch(i -> i > 0);
    }
}
