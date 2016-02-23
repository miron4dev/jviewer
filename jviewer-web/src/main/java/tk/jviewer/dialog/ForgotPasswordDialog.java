package tk.jviewer.dialog;

import org.apache.log4j.Logger;
import tk.jviewer.business.api.UpdateProfileService;
import tk.jviewer.business.model.JViewerBusinessException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import java.io.Serializable;

/**
 * Forgot Password dialog backing bean.
 */
public class ForgotPasswordDialog implements Serializable {

    private static final long serialVersionUID = 7696413119083173191L;
    private static final Logger logger = Logger.getLogger(ForgotPasswordDialog.class);

    private String email;

    private UpdateProfileService updateProfileService;

    /**
     * Resets the user password, sends the login link by email.
     */
    public void resetPassword() {
        try {
            updateProfileService.sendResetPasswordRequest(email);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "The confirmation message has been sent to your email.", null));
        } catch (MessagingException e) {
            logger.error("Exception occurred during sending the password reset request", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Could not send the message because of connection problem. Please contact with your system administrator.", null));
        } catch (JViewerBusinessException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "User with the specified email doesn't exist", null));
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //
    // Dependency Injection
    //

    public void setUpdateProfileService(UpdateProfileService updateProfileService) {
        this.updateProfileService = updateProfileService;
    }
}
