package airport.cac226.rxr353;

import org.jetbrains.annotations.NotNull;

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
        if (routeTime == null)
            return false;
        return true;
    }

    public LocalTime getTime(){
        if (Objects.equals(UNKNOWN(), false))
                throw new IllegalStateException("The route time is not known");
         return LocalTime.now();
    }

    public RouteTime plus(Duration duration){
        if (duration.isZero())
             return UNKNOWN();

        else
            return new RouteTime(routeTime.plus(duration));
    }

    @Override
    public int compareTo(@NotNull RouteTime o) {
        return 0;
    }
}
