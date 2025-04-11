package progressive_overlords.utils;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeDiffHelper {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    public static String getTimeDifference(String startDate, String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate, FORMATTER);
        LocalDateTime end = LocalDateTime.parse(endDate, FORMATTER);

        Duration duration = Duration.between(start, end);
        long seconds = duration.getSeconds();

        if (seconds < 60) {
            return seconds + "secs";
        }

        long minutes = seconds / 60;
        if (minutes < 60) {
            return minutes + "min";
        }

        long hours = minutes / 60;
        long remainingMinutes = minutes % 60;

        if (remainingMinutes == 0) {
            return hours + "h";
        }

        return hours + "h " + remainingMinutes + "min";
    }

}
