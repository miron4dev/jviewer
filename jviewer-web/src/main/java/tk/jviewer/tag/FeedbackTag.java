package tk.jviewer.tag;

import org.apache.log4j.Logger;
import tk.jviewer.business.api.FeedbackService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import java.io.Serializable;

/**
 * Feedback tag backing bean.
 */
public class FeedbackTag implements Serializable {

    private static final long serialVersionUID = -4759174795962760617L;
    private static final Logger logger = Logger.getLogger(FeedbackTag.class);

    private String name;
    private String email;
    private String text;

    private FeedbackService feedbackService;

    /**
     * Submits the feedback.
     */
    public void submit() {
        try {
            feedbackService.sendFeedback(name, email, text);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Thank you for your feedback!", null));
        } catch (MessagingException e) {
            logger.error("Exception occurred during sending a feedback", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Could not send the message because of connection problem. Please contact with your system administrator.", null));
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //
    // Dependency Injection
    //

    public void setFeedbackService(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
}
