package airport.cac226.rxr353;

/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      SimpleFlight
 */


import java.time.Duration;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

public final class FlightPolicy extends AbstractFlight {

    private final Flight flight;
    private final BiFunction<SeatConfiguration, FareClass, SeatConfiguration> policy;

    private FlightPolicy(Flight flight, BiFunction<SeatConfiguration, FareClass, SeatConfiguration> policy) {
        this.flight = flight;
        this.policy = policy;
    }

    /**
     * Builder method since the Constructor is private
     * Requires Flight and policy to not be null
     * @param flight
     * @param policy
     * @return new Flight Policy Object
     */
    public static final FlightPolicy of(Flight flight, BiFunction<SeatConfiguration, FareClass, SeatConfiguration> policy){
        Objects.requireNonNull(flight, "Flight cannot be empty");
        Objects.requireNonNull(policy, "Need a non null Flight Policy");

        return new FlightPolicy(flight, policy);
    }

    /**
     *
     * @param flight
     * @return
     */
    public static final Flight strict(Flight flight) {
        Objects.requireNonNull(flight, "Flight cannot be null");
        BiFunction<SeatConfiguration, FareClass, SeatConfiguration> strictPolicy = (sc, fc) -> {
            EnumMap seats = new EnumMap<SeatClass, Integer>(SeatClass.class);
            seats.put(fc.getSeatClass(), sc.seats(fc.getSeatClass()));
            return SeatConfiguration.of(seats);
        };
        return FlightPolicy.of(flight, strictPolicy);
    }

    public static final Flight restrictedDuration(Flight flight, Duration durationMax) {
        Objects.requireNonNull(flight, "Flight cannot be null");
        Objects.requireNonNull(durationMax, "Restricted Duration flight policy must have a max duration");

        BiFunction<SeatConfiguration, FareClass, SeatConfiguration> strictPolicy = (sc, fc) -> {
            EnumMap seats = new EnumMap<SeatClass, Integer>(SeatClass.class);
            if(flight.isShort(durationMax)) {
                //implement a strict policy
                seats.put(fc.getSeatClass(), sc.seats(fc.getSeatClass()));
            } else {
                // clone the existing seat configuration
                for(SeatClass seatClass : SeatClass.values()) {
                    seats.put(seatClass, sc.seats(seatClass));
                }
            }

            return SeatConfiguration.of(seats);
        };
        return FlightPolicy.of(flight, strictPolicy);
    }

    public static final Flight reserve(Flight flight, int reserve) {
        Objects.requireNonNull(flight, "Flight cannot be null");
        Objects.requireNonNull(reserve, "Must specify reserve");

        BiFunction<SeatConfiguration, FareClass, SeatConfiguration> reservePolicy = (sc, fc) -> {
            EnumMap seats = new EnumMap<SeatClass, Integer>(SeatClass.class);
            for(SeatClass seatClass : SeatClass.values()) {
                seats.put(seatClass, Math.max(sc.seats(seatClass) - reserve, 0));
            } // TODO: CHANGE THIS TO A STREAM
            return SeatConfiguration.of(seats);
        };
        return FlightPolicy.of(flight, reservePolicy);
    }

    public static final Flight limited(Flight flight, int reserve) {
        Objects.requireNonNull(flight, "Flight cannot be null");
        Objects.requireNonNull(reserve, "Must specify reserve");

        BiFunction<SeatConfiguration, FareClass, SeatConfiguration> limitedPolicy = (sc, fc) -> {
            EnumMap seats = new EnumMap<SeatClass, Integer>(SeatClass.class);
            List<SeatClass> seatClassArray = Arrays.asList(SeatClass.values());
            for(int i = 0; i < seatClassArray.size(); i++) {
                SeatClass tempSeatClass = seatClassArray.get(i);
                if(i < seatClassArray.size() - 1)
                {
                    SeatClass nextSeatClass = seatClassArray.get(i + 1);
                    seats.put(tempSeatClass, sc.seats(tempSeatClass) + sc.seats(nextSeatClass));
                } else {
                    seats.put(tempSeatClass, sc.seats(tempSeatClass));
                }
            }
            return SeatConfiguration.of(seats);
        };
        return FlightPolicy.of(flight, limitedPolicy);
    }

    /**
     * CUSTOM POLICY 1
     *
     * Implements a flight policy that gives seats available depending on the identifier
     * Used for the fare class. If the FareClass identifier is greater than or equal to the
     * fareCutOff, then all seats are available
     * */
    public static final Flight pricePolicy(Flight flight, int fareCutOff) {
        Objects.requireNonNull(flight, "Flight cannot be null");

        BiFunction<SeatConfiguration, FareClass, SeatConfiguration> pricePolicy = (sc, fc) -> {
            EnumMap seats = new EnumMap<SeatClass, Integer>(SeatClass.class);
            if(fareCutOff <= fc.getIdentifier()) {
                for(SeatClass seatClass : SeatClass.values()) {
                    seats.put(seatClass, sc.seats(seatClass));
                }
            } else {
                seats.put(fc.getSeatClass(), sc.seats(fc.getSeatClass()));
            }
            return SeatConfiguration.of(seats);
        };
        return FlightPolicy.of(flight, pricePolicy);
    }

    /**
     * CUSTOM POLICY 2
     *
     * Implements a flight policy that only lets people upgrade from a given flight class
     * For example, if there are 4 available seats in business, 3 in premium, and 5 in economy,
     * then an economy passenger should see 4 seats in business, 3 in premium, and 0 in economy.
     * A person in business would see no upgrade seats availible
     * */
    public static final Flight upgradeOnlyPolicy(Flight flight) {
        Objects.requireNonNull(flight, "Flight cannot be null");
        BiFunction<SeatConfiguration, FareClass, SeatConfiguration> pricePolicy = (sc, fc) -> {
            EnumMap seats = new EnumMap<SeatClass, Integer>(SeatClass.class);
            int upgradeSeatsAvailable = 0;
            for(SeatClass seatClass : SeatClass.values()) {
                seats.put(seatClass, upgradeSeatsAvailable);
                upgradeSeatsAvailable += sc.seats(seatClass);
            }
            return SeatConfiguration.of(seats);
        };
        return FlightPolicy.of(flight, pricePolicy);
    }

    /**
     * Auto-generated getter method
     * @return Flight Code
     */
    @Override
    public String getCode() {
        return flight.getCode();
    }

    /**
     * Auto-generated getter method
     * @return Flight leg
     */
    @Override
    public Leg getLeg() {
        return flight.getLeg();
    }

    /**
     * Auto-generated getter method
     * @return Flight Schedule
     */
    @Override
    public FlightSchedule getFlightSchedule() {
        return flight.getFlightSchedule();
    }

    /**
     * Returns available seats
     * @param fareclass
     * @return
     */
    public SeatConfiguration seatsAvailable(FareClass fareclass) {
        Objects.requireNonNull(fareclass, "Fareclass cannot be null");
        return policy.apply(flight.seatsAvailable(fareclass), fareclass);
    }
}
