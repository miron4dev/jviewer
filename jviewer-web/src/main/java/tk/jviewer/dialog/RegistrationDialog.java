package tk.jviewer.dialog;

import org.springframework.dao.DataIntegrityViolationException;
import tk.jviewer.business.api.RegistrationService;
import tk.jviewer.business.model.JViewerBusinessException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import java.io.Serializable;

import static tk.jviewer.model.JViewerUriPath.LOGIN_PAGE;

/**
 * Serves for "registration" use case.
 */
public class RegistrationDialog implements Serializable {

    private static final long serialVersionUID = -6429124304116355292L;

    private String name;
    private String email;
    private String password;

    private RegistrationService registrationService;

    public String createProfile() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            registrationService.sendEmailConfirmation(name, password, email);
            facesContext.getExternalContext().getFlash().setKeepMessages(true);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration is not finished yet. "
                + "Please check your email", null));
            return LOGIN_PAGE.getJsfUri();
        } catch (DataIntegrityViolationException | JViewerBusinessException e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "User with that name or email is already exist.",
                null));
        } catch (MessagingException e) {
            throw new JViewerBusinessException("Could not send email message", e);
        }
        password = null;
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
}
