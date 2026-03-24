import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Event {
    private final String title;
    private final LocalDateTime start;
    private final long durationMinutes;
    private final ZoneId zone;
    private final String track;

    public Event(String title, LocalDateTime start, long durationMinutes, String zoneId, String track) {
        this.title = title;
        this.start = start;
        this.durationMinutes = durationMinutes;
        this.zone = ZoneId.of(zoneId);
        this.track = track;
    }

    public LocalDateTime end() {
        return start.plusMinutes(durationMinutes);
    }

    public String label() {
        return title + " [" + track + "]";
    }

    public String getTitle() { return title; }
    public LocalDateTime getStart() { return start; }
    public long getDurationMinutes() { return durationMinutes; }
    public ZoneId getZone() { return zone; }
    public String getTrack() { return track; }

    @Override
    public String toString() {
        return label() + " at " + start + " (" + zone + ")";
    }
}