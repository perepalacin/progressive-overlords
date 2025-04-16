package progressive_overlords.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateFormatter {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    public static String formatToFriendlyDate(String dateStr) {
        if (dateStr.contains(".")) {
            int dotIndex = dateStr.indexOf(".");
            String beforeDot = dateStr.substring(0, dotIndex);
            String afterDot = dateStr.substring(dotIndex + 1);
            afterDot = (afterDot + "000000").substring(0, 6); // pad and trim
            dateStr = beforeDot + "." + afterDot;
        }
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, INPUT_FORMATTER);

        String dayOfWeek = dateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int dayOfMonth = dateTime.getDayOfMonth();
        String suffix = getDayOfMonthSuffix(dayOfMonth);
        String month = dateTime.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int year = dateTime.getYear();

        return String.format("%s, %d%s of %s %d", dayOfWeek, dayOfMonth, suffix, month, year);
    }

    private static String getDayOfMonthSuffix(int n) {
        if (n >= 11 && n <= 13) return "th";
        return switch (n % 10) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };
    }
}
