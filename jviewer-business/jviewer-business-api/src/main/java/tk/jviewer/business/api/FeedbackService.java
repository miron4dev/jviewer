package tk.jviewer.business.api;

import javax.mail.MessagingException;

/**
 * Provides the method to send a feedbacks.
 */
public interface FeedbackService {

    /**
     * Sends a feedback to the administrator email.
     *
     * @param name  name of the user.
     * @param email email of the user.
     * @param text  text of the user feedback.
     */
    void sendFeedback(String name, String email, String text) throws MessagingException;
}
