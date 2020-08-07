package patinentintake.notifier;

import patinentintake.ClinicCalendar;
import patinentintake.PatientAppointment;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

class UpcomingAppointmentNotifier {
    private ClinicCalendar clinicCalendar;
    private EmailNotifier sender;

    UpcomingAppointmentNotifier(ClinicCalendar clinicCalendar, EmailNotifier sender) {
        this.clinicCalendar = clinicCalendar;
        this.sender = sender;
    }

    void run() {
        clinicCalendar.getTodayAppointments().forEach(this::sendNotificationForAppointment);
    }

    private void sendNotificationForAppointment(PatientAppointment appt) {
        String email = appt.getPatientLastName().toLowerCase() + appt.getPatientFirstName().toLowerCase() + "@mail.com";
        System.out.println("Sending with body: " + buildMessageBody(appt));
        sender.sendNotification("Appointment reminder", buildMessageBody(appt), email);
    }

    private String buildMessageBody(PatientAppointment appt) {
        return "You have an appointment tomorrow at "
                + appt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("h:m a", Locale.US))
                + "with Dr."
                + appt.getDoctor().getName() + ".";
    }
}
