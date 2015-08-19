package tk.jviewer.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import tk.jviewer.controller.LoginController;
import tk.jviewer.profile.UserProfile;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Login controller implementation.
 * @author Evgeny Mironenko
 */
public class LoginControllerImpl implements LoginController {

    private static final Logger LOG = Logger.getLogger(LoginControllerImpl.class);

    private UserProfile userProfile;
    private AuthenticationManager authenticationManager;

    /**
     * Prepares user for login to system.
     * @return URL for redirect. In BadCredentialsException cases return null and show error message.
     */
    @Override
    public String login() {
        try{
            authentication();
            return "main?faces-redirect=true";
        } catch (BadCredentialsException e){
            LOG.warn("Host: " + ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteHost() + " | Authentication was failed. More: " + e);
            return null;
        }
    }

    /**
     * Invalidate current session and logout user from system.
     * @return main page URL for redirect.
     */
    @Override
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        SecurityContextHolder.getContext().setAuthentication(null);
        return "index?faces-redirect=true";
    }

    /**
     * @see tk.jviewer.controller.LoginController#pageRedirect(String)
     * Cancels redirect, if no one rooms was selected.
     */
    @Override
    public String pageRedirect(String page){
        return page + "?faces-redirect=true";
    }

    /**
     * User authentication.
     */
    private void authentication() {
        Authentication request = new UsernamePasswordAuthenticationToken(userProfile.getName(), userProfile.getPassword());
        Authentication result = authenticationManager.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(result);
    }

    //
    // Setters for Dependency Injection.
    //

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
