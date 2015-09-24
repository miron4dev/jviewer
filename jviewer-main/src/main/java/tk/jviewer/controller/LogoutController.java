package tk.jviewer.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.context.FacesContext;

/**
 * Logout controller implementation.
 * @author Evgeny Mironenko
 */
public class LogoutController {

    /**
     * Invalidates current session and does logout.
     * @return main page URL for redirect.
     */
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        SecurityContextHolder.getContext().setAuthentication(null);
        return "index?faces-redirect=true";
    }

}
