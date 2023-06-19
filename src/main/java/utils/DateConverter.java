package utils;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Utility class for converting date and time strings to LocalDate and LocalTime objects.
 */
public class DateConverter {
    /**
     * Converts a date string in the format "YYYY-MM-DD" to a LocalDate object.
     *
     * @param date the date string to convert
     * @return the converted LocalDate object
     */
    public static LocalDate convertStringToLocalDate(String date) {
        String[] array = date.split("-");
        LocalDate result = LocalDate.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]),
                Integer.parseInt(array[2]));
        return result;
    }

    /**
     * Converts a time string in the format "HH:MM" to a LocalTime object.
     *
     * @param time the time string to convert
     * @return the converted LocalTime object
     */
    public static LocalTime convertStringToLocalTime(String time) {
        String[] array = time.split(":");
        LocalTime result = LocalTime.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
        return result;
    }
}
