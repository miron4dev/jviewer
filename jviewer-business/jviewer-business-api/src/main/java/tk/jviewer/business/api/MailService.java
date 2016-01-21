package tk.jviewer.business.api;

import javax.mail.MessagingException;

/**
 * Provides the methods to work with Mail.
 */
public interface MailService {


    /**
     * Sends the mail message to the specified recipient.
     *
     * @param recipient recipient of email.
     * @param subject   subject of the message.
     * @param content   text of the message.
     * @throws MessagingException if the sending has failed.
     */
    void sendMessage(String recipient, String subject, String content) throws MessagingException;
}
