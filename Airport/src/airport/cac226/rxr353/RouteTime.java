package airport.cac226.rxr353;

//import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public final class RouteTime implements Comparable<RouteTime>{

    private final LocalTime routeTime;

    public RouteTime(LocalTime routeTime) {
        this.routeTime = routeTime;
    }

    public static final RouteTime UNKNOWN(){
        return null;
    }

    public boolean isKnown(){
        return !(routeTime == null);
    }

    public LocalTime getTime(){
        if (!isKnown())
            throw new IllegalStateException("The route time is not known");
        return LocalTime.now();
    }

    public RouteTime plus(Duration duration){
        Objects.requireNonNull(duration);
        if (!isKnown())
             return UNKNOWN();
        return new RouteTime(routeTime.plus(duration));
    }

    // ASK ELLIS IF THIS IS LEGIT???
    @Override
    public int compareTo(RouteTime o) {
        if (!isKnown()) {
            if(!o.isKnown())
                return 0;
            else
                return -1;
        }
        if(!o.isKnown())
            return 1;
        return routeTime.compareTo(o.getTime());
    }
}
