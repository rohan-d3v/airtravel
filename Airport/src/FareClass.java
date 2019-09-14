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

    public static final FareClass of(int identifier, SeatClass seatClass){
        Objects.requireNonNull(identifier, "Identifier needs to be non null");
        Objects.requireNonNull(seatClass, "SeatClass needs to exist");

        return new FareClass (identifier, seatClass);
    }


    public int getIdentifier() {
        return identifier;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FareClass fareClass = (FareClass) o;
        return identifier == fareClass.identifier &&
                seatClass == fareClass.seatClass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, seatClass);
    }




}
