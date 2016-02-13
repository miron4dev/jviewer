package tk.jviewer.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.jviewer.business.api.FeedbackService;
import tk.jviewer.business.api.MailService;

import javax.mail.MessagingException;
import java.text.MessageFormat;

/**
 * Implementation of {@link FeedbackService}.
 */
@Component
public class FeedbackServiceImpl implements FeedbackService {

    private static final String ADMIN_EMAIL = "emironen0@gmail.com";
    private static final String MAIL_SUBJECT = "New JViewer Feedback";
    private static final String MAIL_TEXT = "Dear user,\nYou''ve got a new JViewer feedback from the user with name: {0}, email: {1}\n" +
        "Text of the feedback:\n{2}";

    @Autowired
    private MailService mailService;

    @Override
    public void sendFeedback(String name, String email, String text) throws MessagingException {
        mailService.sendMessage(ADMIN_EMAIL, MAIL_SUBJECT, MessageFormat.format(MAIL_TEXT, name, email, text));
    }
}
