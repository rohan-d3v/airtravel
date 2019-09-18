package airport.cac226.rxr353;

/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      SeatConfiguration
 */


import java.util.Collection;
import java.util.EnumMap;
import java.util.Objects;

public final class SeatConfiguration {
    private final EnumMap<SeatClass, Integer> seats;

    private SeatConfiguration(EnumMap<SeatClass, Integer> seats) {
        this.seats = seats.clone();
    }

    /**
     * Builder method since the Constructor is private
     * Checks if the Seat Map is null
     * @param seats
     * @return object type SeatConfiguration
     */
    public static SeatConfiguration of(EnumMap<SeatClass, Integer> seats) {
        Objects.requireNonNull(seats, "Seats enum map cannot be null");
        return new SeatConfiguration(seats);
    }

    /**
     * Second Build Of Method
     * Makes a copy of the seatConfiguration object
     * @param seatConfiguration
     * @return seatConfiguration containing enumMap
     */
    public static SeatConfiguration of(SeatConfiguration seatConfiguration) {
        Objects.requireNonNull(seatConfiguration, "Seats configuration cannot be null");
        EnumMap<SeatClass, Integer> seats = new EnumMap<SeatClass, Integer>(SeatClass.class);

        for(SeatClass seatClass : SeatClass.values()) {
            seats.put(seatClass, seatConfiguration.seats(seatClass));
        }
        return new SeatConfiguration(seats);
    }

    /**
     * Checks if the SeatClass is valid before returning Seat Class
     * @param seatClass
     * @return int
     */
    public int seats(SeatClass seatClass) {
        Objects.requireNonNull(seatClass, "Seat class cannot be null");
        if(seats.containsKey(seatClass)) {
            return seats.get(seatClass);
        }
        return 0;
    }

    /**
     * Sets a new seat Class, after checking available seats
     * @param seatClass
     * @param seats
     * @return seats available in class
     */
    public int setSeats(SeatClass seatClass, int seats) {
        Objects.requireNonNull(seatClass, "Seat Class cannot be null");
        int initialSeatsAvailable = seats(seatClass);
        this.seats.put(seatClass, seats);
        return initialSeatsAvailable;
    }

    /**
     * Rechecks if the class has seats
     * @return True or false if seats are available
     */
    public boolean hasSeats() {
        Collection<Integer> seatsAvailable = seats.values();
        return seatsAvailable.stream().anyMatch(i -> i > 0);
    }
}
