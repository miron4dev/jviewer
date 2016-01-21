package tk.jviewer.dialog;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

import static tk.jviewer.model.JViewerUriPath.INDEX_PAGE;
import static tk.jviewer.servlet.RegistrationServlet.REGISTRATION_RESULT;

/**
 * Login Dialog backing bean.
 */
public class LoginDialog implements Serializable {

    private static final long serialVersionUID = 1476680699366683821L;
    private static final Logger logger = Logger.getLogger(LoginDialog.class);

    private String name;
    private String password;

    private AuthenticationManager authenticationManager;

    public void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        FacesMessage facesMessage = (FacesMessage) session.getAttribute(REGISTRATION_RESULT);
        if (facesMessage != null) {
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            session.setAttribute(REGISTRATION_RESULT, null);
        }
    }

    /**
     * Login to JViewer.
     *
     * @return URL for redirect.
     */
    public String login() {
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(name, password);
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            return INDEX_PAGE.getJsfUri();
        } catch (BadCredentialsException e) {
            logger.error("Authentication failed", e);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User data is invalid!", null));
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
