package airport.cac226.rxr353;

//import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public final class RouteTime implements Comparable<RouteTime>{

    private final LocalTime routeTime;
    private Boolean known;

    public RouteTime(LocalTime routeTime) {

        this.routeTime = routeTime;
        known = true;
    }

    // private constructor to make an unkown routetime
    private RouteTime() {
        routeTime = null;
        known = false;
    }

    public static final RouteTime UNKNOWN(){
        return new RouteTime();
    }

    public boolean isKnown(){
        return known;
    }

    public LocalTime getTime(){
        if (!isKnown())
            throw new IllegalStateException("The route time is not known");
        return routeTime;
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
        Objects.requireNonNull(o);
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
