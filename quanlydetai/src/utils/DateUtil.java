package utils;

import java.time.LocalDate;

public class DateUtil {
    public static LocalDate parseDate(String date)
    {
        return date != null ? LocalDate.parse(date) : null;
    }
}