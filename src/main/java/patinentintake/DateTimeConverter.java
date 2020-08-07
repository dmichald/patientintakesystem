package patinentintake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeConverter {
     public static LocalDateTime convertToDateFromString(String dateTime, LocalDate today) {
        LocalDateTime localDateTime;
        try {
            if (dateTime.toLowerCase().startsWith("today")) {
                String[] parts = dateTime.split(" ", 2);
                LocalTime time = LocalTime.parse(parts[1].toUpperCase(), DateTimeFormatter.ofPattern("h:m a", Locale.US));
                localDateTime = LocalDateTime.of(today, time);
            } else {
                localDateTime = LocalDateTime.parse(dateTime.toUpperCase(), DateTimeFormatter.ofPattern("M/d/yyyy h:m a", Locale.US));
            }
        } catch (Throwable t) {
            throw new RuntimeException("Please enter correct date format [M/d/yyyy h:mm a");
        }
        return localDateTime;
    }
}
