package tk.jviewer.dialog;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import sun.rmi.runtime.Log;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Login Dialog backing bean.
 */
public class LoginDialog implements Serializable {

    private static final long serialVersionUID = 1476680699366683821L;
    private static final Logger logger = Logger.getLogger(LoginDialog.class);

    private String name;
    private String password;

    private AuthenticationManager authenticationManager;

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
            return "index?faces-redirect=true";
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
