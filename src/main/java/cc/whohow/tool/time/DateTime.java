package cc.whohow.tool.time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    private static final DateTimeFormatter DEFAULT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private ZonedDateTime dateTime;

    private DateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static DateTime of(Instant instant) {
        return new DateTime(ZonedDateTime.ofInstant(instant, ZoneId.systemDefault()));
    }

    public static DateTime ofEpochSecond(long epochSecond) {
        return of(Instant.ofEpochSecond(epochSecond));
    }

    public String toString() {
        return toString(DEFAULT);
    }

    public String toString(DateTimeFormatter formatter) {
        return dateTime.format(formatter);
    }
}
