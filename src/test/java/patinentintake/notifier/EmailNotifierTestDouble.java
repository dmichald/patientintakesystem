package patinentintake.notifier;

import java.util.ArrayList;
import java.util.List;

public class EmailNotifierTestDouble implements EmailNotifier {
    List<Message> receivedMessage = new ArrayList<>();


    @Override
    public void sendNotification(String subject, String body, String address) {
        receivedMessage.add(new Message(address, subject, body));
    }

    class Message {
         String toAddress;
         String subject;
         String body;

        Message(String toAddress, String subject, String body) {
            this.toAddress = toAddress;
            this.subject = subject;
            this.body = body;
        }
    }
}
