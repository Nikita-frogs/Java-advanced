import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeLab {

    public static Instant toInstant(Event e) {
        return ZonedDateTime.of(e.getStart(), e.getZone()).toInstant();
    }

    public static long minutesBetween(Event a, Event b) {
        Instant startA = toInstant(a);
        Instant startB = toInstant(b);
        Duration duration = Duration.between(startA, startB);
        return Math.abs(duration.toMinutes());
    }

    public static ZonedDateTime startInZone(Event e, String zone) {
        ZonedDateTime originalTime = ZonedDateTime.of(e.getStart(), e.getZone());
        return originalTime.withZoneSameInstant(ZoneId.of(zone));
    }
}