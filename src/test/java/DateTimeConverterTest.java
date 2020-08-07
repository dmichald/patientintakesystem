import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import patinentintake.DateTimeConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@DisplayName("patinentintake.DateTimeConverter should")
class DateTimeConverterTest {

    @Nested
    @DisplayName("convert string with 'today' ")
    class TodayTests{
        @Test
        @DisplayName("correctly")
        void convertTodayStringCorrectly() {
            LocalDate today = LocalDate.of(2020, 8, 6);
            LocalDateTime result = DateTimeConverter.convertToDateFromString("today 1:00 pm", today);
            assertEquals(result, LocalDateTime.of(2020, 8, 6, 13, 0),
                    "Failed to convert 'today' string to expected date time, today passed val: " + today);
        }

        @Test
        @DisplayName("regardless od case")
        void convertTodayStringCorrectlyCaseSensitive() {
            LocalDate today = LocalDate.of(2020, 8, 6);
            LocalDateTime result = DateTimeConverter.convertToDateFromString("today 1:00 pm", today);
            assertEquals(result, LocalDateTime.of(2020, 8, 6, 13, 0),
                    "Failed to convert 'today' string to expected date time, today passed val: " + today);
        }
    }


    @Test
    @DisplayName("convert expected date time in string correctly")
    void convertCorrectPatternToDateTime(){
        LocalDateTime result = DateTimeConverter.convertToDateFromString("8/6/2020 1:00 pm", LocalDate.of(2020, 8, 6));
        assertEquals(result,LocalDateTime.of(2020,8,6,13,0));
    }

    @Test
    @DisplayName("throw exception if entered pattern of string incorrect")
    void throwExceptionsIfIncorrectPatternProvided(){
      Throwable throwable=  assertThrows(RuntimeException.class,() -> DateTimeConverter.convertToDateFromString("8:6/2020 1:00 pm",
              LocalDate.of(2020, 8, 6)));

      assertEquals("Please enter correct date format [M/d/yyyy h:mm a", throwable.getMessage());
    }

}