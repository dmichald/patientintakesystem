import org.junit.jupiter.api.*;

import patinentintake.ClinicCalendar;
import patinentintake.Doctor;
import patinentintake.PatientAppointment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Tag("datetime")
@DisplayName("Clinic calendar should")
class ClinicCalendarTest {

    private ClinicCalendar calendar;

    @BeforeEach
    void init() {
        calendar = new ClinicCalendar(LocalDate.of(2020, 8, 6));

    }

    @Test
    @DisplayName("record a new appointment correctly")
    void allowEntryOfAnAppointments() {
        calendar.addAppointment("Jan", "Baxter", "jan", "09/04/2020 2:00 pm");
        List<PatientAppointment> appointments = calendar.getAppointments();
        PatientAppointment appointment = appointments.get(0);
        assertEquals("Jan", appointment.getPatientFirstName());
        assertAll(
                () -> assertNotNull(appointments),
                () -> assertEquals(1, appointments.size()),
                () -> assertEquals(Doctor.jan, appointment.getDoctor()),
                () -> assertEquals("9/4/2020 02:00 PM", appointment.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")))
        );
    }




    @Nested
    @DisplayName("indicate if there are appointments correctly")
    class HasAppointments{
        @Test
        @DisplayName("when there are appointments")
        void returnTrueForHasAppointmentsIfThereAreAppointments() {
            calendar.addAppointment("Jan", "Baxter", "jan", "09/04/2020 2:00 pm");
            assertTrue(calendar.hasAppointment(LocalDate.of(2020, 9, 4)));
        }

        @Test
        @DisplayName("when there are no appointments")
        void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
            assertFalse(calendar.hasAppointment(LocalDate.of(2020, 9, 4)));
        }
    }


    @Test
    @DisplayName("return today appointments")
    void returnCurrentDaysAppointment() {
        calendar.addAppointment("Jan", "Baxter", "jan", "08/06/2020 1:00 pm");
        calendar.addAppointment("Jan", "Baxter", "jan", "08/06/2020 2:00 pm");
        calendar.addAppointment("Jan", "Baxter", "jan", "09/04/2020 3:00 pm");
        assertEquals(2, calendar.getTodayAppointments().size());
    }

    @Nested
    @DisplayName(" return upcoming appointments")
    class UpcomingAppointments{
        @Test
        @DisplayName("as empty list when are none")
        void whenThereAreNone(){
            List<PatientAppointment> appointments = calendar.getUpcomingAppointments();
            assertEquals(0,appointments.size());
        }

        @Test
        @DisplayName("correctly when are some in the past ")
        void whenThereAreSomePastAndFutureAppointment(){
            calendar.addAppointment("Jan", "Kowal", "jan",
                    "07/27/2017 2:00 pm");
            calendar.addAppointment("Jan", "Kowal", "jan",
                    "07/27/2018 2:00 pm");
            calendar.addAppointment("Jan", "Kowal", "jan",
                    "08/27/2020 2:00 pm");
            assertEquals(1, calendar.getUpcomingAppointments().size());
        }

    }
}