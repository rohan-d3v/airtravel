/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      Leg
 */
public final class Leg {
    private final Airport origin;
    private final Airport destination;

    private Leg(Airport m_origin, Airport m_destination)
    {
        origin = m_origin;
        destination = m_destination;
    }

    public static final Leg of(Airport m_origin, Airport m_destination) {
        if(m_origin == null || m_destination == null) {
            throw new NullPointerException("Airport origin/destination cannot be null");
        }
        return new Leg(m_origin, m_destination);
    }

    public Airport origin()
    {
        return origin;
    }

    public Airport destination()
    {
        return destination;
    }
}