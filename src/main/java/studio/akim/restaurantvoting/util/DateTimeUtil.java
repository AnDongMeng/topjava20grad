package studio.akim.restaurantvoting.util;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeUtil {
    private static final LocalTime TIME_BEFORE_CHANGE = LocalTime.of(11, 00);

    public static LocalTime currentTime() {
        return LocalTime.now();
    }

    public static LocalDate currentDate() {
        return LocalDate.now();
    }

    public static boolean userCanChangeVote() {
        return currentTime().isBefore(TIME_BEFORE_CHANGE);
    }
}
