package tk.jviewer.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * JViewer Mail Client.
 */
public class MailClient {

    private static final Properties MAIL_PROPERTIES;
    private static final String SENDER = "no-reply@jviewer.tk";

    static {
        MAIL_PROPERTIES = System.getProperties();
        MAIL_PROPERTIES.put("mail.smtp.host", "95.85.32.78");
        MAIL_PROPERTIES.put("mail.smtp.port", 587);
        MAIL_PROPERTIES.put("mail.smtp.auth", "false");
        MAIL_PROPERTIES.put("mail.mime.charset", "UTF-8");
        MAIL_PROPERTIES.put("mail.debug", "false");
    }

    /**
     * Sends the mail message to the specified recipient.
     *
     * @param recipient recipient of email.
     * @param subject   subject of the message.
     * @param content   text of the message.
     */
    public void sendMessage(String recipient, String subject, String content) throws MessagingException {
        Session session = Session.getDefaultInstance(MAIL_PROPERTIES);

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(SENDER));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        msg.setSubject(subject);
        msg.setText(content);
        Transport.send(msg);
    }
}
