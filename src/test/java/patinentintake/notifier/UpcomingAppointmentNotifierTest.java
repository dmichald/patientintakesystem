package patinentintake.notifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patinentintake.ClinicCalendar;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UpcomingAppointmentNotifierTest {
    private EmailNotifierTestDouble emailNotifier;

    @BeforeEach
    void init() {
        emailNotifier = new EmailNotifierTestDouble();
    }

    @Test
    void sendNotificationWithFormat() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.of(2020, 8, 26));
        calendar.addAppointment("Jan", "Kowal", "jan", "08/26/2020 2:00 pm");
        UpcomingAppointmentNotifier notifier = new UpcomingAppointmentNotifier(calendar, emailNotifier);
        notifier.run();

        assertEquals(1, emailNotifier.receivedMessage.size());

        EmailNotifierTestDouble.Message expectedMessage = emailNotifier.receivedMessage.get(0);
        assertAll(
                () -> assertEquals("kowaljan@mail.com", expectedMessage.toAddress),
                () -> assertEquals("Appointment reminder", expectedMessage.subject),
                () ->assertEquals("You have an appointment tomorrow at 2:0 PM"
                        + "with Dr.Jan Kovalsky.",expectedMessage.body)
                );
    }
}