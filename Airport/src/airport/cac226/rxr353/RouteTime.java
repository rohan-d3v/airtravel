package airport.cac226.rxr353;

//import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public final class RouteTime implements Comparable<RouteTime>{

    private final LocalTime routeTime;

    public static final RouteTime UNKNOWN = new RouteTime(null);

    public RouteTime(LocalTime routeTime) {
        this.routeTime = routeTime;
    }

    public boolean isKnown(){
        return routeTime != null;
    }

    public LocalTime getTime(){
        if (!isKnown())
            throw new IllegalStateException("The route time is not known");
        return routeTime;
    }

    public RouteTime plus(Duration duration){
        Objects.requireNonNull(duration);
        if (!isKnown())
             return UNKNOWN;
        return new RouteTime(routeTime.plus(duration));
    }

    @Override
    public int compareTo(RouteTime o) {
        int boolCompare = Boolean.compare(isKnown(), o.isKnown());
        if(boolCompare != 0) {
            return (int) boolCompare;
        }
        if(!isKnown())
            return 0;
        return routeTime.compareTo(o.getTime());

    }
}
