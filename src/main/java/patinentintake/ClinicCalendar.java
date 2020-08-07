package patinentintake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClinicCalendar {
    private List<PatientAppointment> appointments = new ArrayList<>();
    private LocalDate today;

    public ClinicCalendar(LocalDate today) {
        this.today = today;
    }

    public void addAppointment(String patientFirstName, String patientLastName, String doctorKey, String dateTime) {
        Doctor doc = Doctor.valueOf(doctorKey.toLowerCase());
        LocalDateTime localDateTime = DateTimeConverter.convertToDateFromString(dateTime, today);
        PatientAppointment appointment = new PatientAppointment(patientFirstName, patientLastName, localDateTime, doc);
        appointments.add(appointment);
    }


    public List<PatientAppointment> getAppointments() {
        return appointments;
    }

    public boolean hasAppointment(LocalDate date) {
        return appointments.stream()
                .anyMatch(patientAppointment -> patientAppointment.getAppointmentDateTime().toLocalDate().equals(date));
    }

    public List<PatientAppointment> getTodayAppointments() {
        return appointments.stream()
                .filter(appt -> appt.getAppointmentDateTime().toLocalDate().equals(today))
                .collect(Collectors.toList());
    }

    public List<PatientAppointment> getUpcomingAppointments() {
        return appointments.stream()
                .filter(patientAppointment -> patientAppointment.getAppointmentDateTime()
                        .toLocalDate()
                        .isAfter(today))
                .collect(Collectors.toList());
    }
}
