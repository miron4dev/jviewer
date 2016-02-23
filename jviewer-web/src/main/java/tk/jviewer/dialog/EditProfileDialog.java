package tk.jviewer.dialog;

import org.apache.log4j.Logger;
import tk.jviewer.business.api.UpdateProfileService;
import tk.jviewer.security.SecurityService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import java.io.Serializable;

/**
 * Edit Profile dialog backing bean.
 */
public class EditProfileDialog implements Serializable {

    private static final long serialVersionUID = 351592517026358879L;
    private static final Logger logger = Logger.getLogger(EditProfileDialog.class);

    private String password;

    private UpdateProfileService updateProfileService;

    /**
     * @see SecurityService#isAdmin()
     */
    public boolean isAdmin() {
        return SecurityService.isAdmin();
    }

    /**
     * @see SecurityService#getUsername()
     */
    public String getUsername() {
        return SecurityService.getUsername();
    }

    /**
     * @see SecurityService#getUserEmail() ()
     */
    public String getEmail() {
        return SecurityService.getUserEmail();
    }

    /**
     * Sends the request to admin permissions.
     */
    public void sendAdminRequest() {
        try {
            updateProfileService.sendAdminRequest(getUsername(), getEmail());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "The request has been sent and will be considered soon.", null));
        } catch (MessagingException e) {
            logger.error("Exception occurred during sending the admin request", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Could not send the message because of connection problem. Please contact with your system administrator.", null));
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //
    // Dependency Injection
    //

    public void setUpdateProfileService(UpdateProfileService updateProfileService) {
        this.updateProfileService = updateProfileService;
    }
}
