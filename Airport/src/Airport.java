import java.time.Duration;

//rohan and caitlin
public  final class Airport {


    String code;
    Duration ConnectionTimeMin;

    private Airport(String code, Duration connectionTimeMin) {
        code = getCode();
        ConnectionTimeMin = getConnectionTimeMin();
    }

    public String getCode() {
        return code;
    }

    public Duration getConnectionTimeMin() {
        return ConnectionTimeMin;
    }
}

