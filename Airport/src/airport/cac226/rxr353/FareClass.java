package airport.cac226.rxr353;

/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      FareClass
 */

import java.util.Objects;

public final class FareClass {

    private final SeatClass seatClass;
    private final int identifier;

    private FareClass(int identifier, SeatClass seatClass) {
        this.identifier = identifier;
        this.seatClass = seatClass;
    }

    /**
     * Accesses the builder method
     * @param identifier checks if null
     * @param seatClass checks if null
     * @return fareclass object
     */
    public static final FareClass of(int identifier, SeatClass seatClass){
        Objects.requireNonNull(identifier, "Identifier needs to be non null");
        Objects.requireNonNull(seatClass, "SeatClass needs to exist");

        return new FareClass (identifier, seatClass);
    }


    /**
     * Standard Getter method
     * Auto-generated
     * @return identifier
     */
    final int getIdentifier() {
        return identifier;
    }

    /**
     * Standard Getter method
     * Auto-generated
     * @return seat Class
     */
    final SeatClass getSeatClass() {
        return seatClass;
    }

    /**
     * Checks if the entered fare class is the same as the existing fare class
     * Creates FareClass object after test
     * @param o
     * @return whether the fare class was created
     */
    @Override
    public boolean equals(Object o) {
        Objects.requireNonNull(o, "Fare Class cannot be null");
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        FareClass fareClass = (FareClass) o;
        return identifier == fareClass.identifier &&
                seatClass == fareClass.seatClass;
    }

    /**
     * Auto-generated
     * @return Has code version of Fare class and seat identifier
     */
    @Override
    public int hashCode() {
        return Objects.hash(identifier, seatClass);
    }




}
