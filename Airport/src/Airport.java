/**
 * @names:      Caitlin Campbell, Rohan Krishna Ramkhumar
 * @case-id:    cac226, rxr353
 * @project:    2) AirTravel
 * @class:      Airport
 */
import java.time.Duration;

public  final class Airport {

    String shortCode;
    String code;
    Duration ConnectionTimeMin;

    private Airport(String code, Duration connectionTimeMin) {
        code = getCode();
        ConnectionTimeMin = getConnectionTimeMin();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return getShortCode();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getCode() {
        return code;
    }

    public Duration getConnectionTimeMin() {
        return ConnectionTimeMin;
    }
}

