package studio.akim.restaurantvoting.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeUtil {
    private static final LocalTime TIME_BEFORE_CHANGE = LocalTime.of(11, 00);
    private static LocalDateTime mockDateTime;
    private static boolean useRealTime = true;

    public static void stdSettings() {
        useRealTime = true;
        mockDateTime = null;
    }

    public static LocalTime currentTime() {
        return useRealTime ? LocalTime.now() : mockDateTime.toLocalTime();
    }

    public static LocalDate currentDate() {
        return useRealTime ? LocalDate.now() : mockDateTime.toLocalDate();
    }

    public static boolean userCanChangeVote() {
        return currentTime().isBefore(TIME_BEFORE_CHANGE);
    }

    public static void setMockDateTime(LocalDateTime mockDateTime) {
        DateTimeUtil.mockDateTime = mockDateTime;
    }


    public static void setUseRealTime(boolean useRealTime) {
        DateTimeUtil.useRealTime = useRealTime;
    }
}
