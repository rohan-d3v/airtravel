package airport.cac226.rxr353;

/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      Leg
 */

import java.util.Objects;

public final class Leg {
    private final Airport origin;
    private final Airport destination;

    private Leg(Airport m_origin, Airport m_destination) {
        origin = m_origin;
        destination = m_destination;
    }

    public static final Leg of(Airport m_origin, Airport m_destination) {
        Objects.requireNonNull(m_origin, "Airport Origin cannot be null");
        Objects.requireNonNull(m_destination, "Airport Destination cannot be null");

        return new Leg(m_origin, m_destination);
    }

    /**
     * Standard Getter method, returns the Origin Airport
     * Auto Generated
     * @return Origin Airport
     */
    public Airport origin()
    {
        return origin;
    }

    /**
     * Standard Getter method, returns the Destination Airport
     * Auto Generated
     * @return Destination Airport
     */
    public Airport destination() {
        return destination;
    }
}