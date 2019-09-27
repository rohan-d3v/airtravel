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

        FlightPolicy result = new FlightPolicy(flight, policy);
        result.getLeg().origin().removeFlight(flight);
        result.getLeg().origin().addFlight(result);
        return result;
    }

    private static final EnumMap<SeatClass, Integer> seatConfigurationFilled(int seatsAvailable) {
        EnumMap seats = new EnumMap<SeatClass, Integer>(SeatClass.class);
        for(SeatClass seatClass : SeatClass.values()) {
            seats.put(seatClass, seatsAvailable);
        }
        return seats;
    }

    /**
     * Returns the seatClass and makes sure passengers are seated in correct class
     * @param flight
     * @return Flight type to determine class
     */
    public static final Flight strict(Flight flight) {
        Objects.requireNonNull(flight, "Flight cannot be null");
        BiFunction<SeatConfiguration, FareClass, SeatConfiguration> strictPolicy = (sc, fc) -> {
            EnumMap seats = seatConfigurationFilled(0);
            seats.put(fc.getSeatClass(), sc.seats(fc.getSeatClass()));
            return SeatConfiguration.of(seats);
        };
        return FlightPolicy.of(flight, strictPolicy);
    }

    /**
     * Makes sure that the Flight returned is specifically of a certain length
     * Returns the FlightPolicy for the specific flight
     * @param flight
     * @param durationMax
     * @return FlightPolicy
     */
    public static final Flight restrictedDuration(Flight flight, Duration durationMax) {
        Objects.requireNonNull(flight, "Flight cannot be null");
        Objects.requireNonNull(durationMax, "Restricted Duration flight policy must have a max duration");

        if(flight.isShort(durationMax)) {
            return strict(flight);
        } else {
            return flight;
        }
    }

    /**
     * Makes sure that the upgraded seats are not in seats already reserved for other passengers
     * @param flight
     * @param reserve
     * @return FlightPolicy/restrictions
     */
    public static final Flight reserve(Flight flight, int reserve) {
        Objects.requireNonNull(flight, "Flight cannot be null");
        Objects.requireNonNull(reserve, "Must specify reserve");

        BiFunction<SeatConfiguration, FareClass, SeatConfiguration> reservePolicy = (sc, fc) -> {
            EnumMap seats = new EnumMap<SeatClass, Integer>(SeatClass.class);
            for(SeatClass seatClass : SeatClass.values()) {
                seats.put(seatClass, Math.max(sc.seats(seatClass) - reserve, 0));
            }
            return SeatConfiguration.of(seats);
        };
        return FlightPolicy.of(flight, reservePolicy);
    }

    /**
     * Makes sure that the Flight seat upgrade only goes up one level and then cuts off
     * @param flight
     * @return FlightPolicy/restrictions
     */
    public static final Flight limited(Flight flight) {
        Objects.requireNonNull(flight, "Flight cannot be null");

        BiFunction<SeatConfiguration, FareClass, SeatConfiguration> limitedPolicy = (sc, fc) -> {
            EnumMap seats = new EnumMap<SeatClass, Integer>(SeatClass.class);
            int seatClassNumber = fc.getSeatClass().ordinal();
            seats.put(fc.getSeatClass(), sc.seats(fc.getSeatClass()));
            if(seatClassNumber > 0) {
                seats.put(SeatClass.values()[seatClassNumber - 1], sc.seats(SeatClass.values()[seatClassNumber - 1]));
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
     * fareCutOff, then all seats are available. Otherwise, a strict policy is implemented
     * */
    public static final Flight pricePolicy(Flight flight, int fareCutOff) {
        Objects.requireNonNull(flight, "Flight cannot be null");

        BiFunction<SeatConfiguration, FareClass, SeatConfiguration> pricePolicy = (sc, fc) -> {
            if(fareCutOff <= fc.getIdentifier()) {
                return sc;
            } else {
                return strict(flight).seatsAvailable(fc);
            }
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
            seats.put(fc.getSeatClass(), sc.seats(fc.getSeatClass()));
            for(SeatClass seatClass : SeatClass.values()) {
                if(fc.getSeatClass().ordinal() > seatClass.ordinal())
                    seats.put(seatClass, sc.seats(seatClass));
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
