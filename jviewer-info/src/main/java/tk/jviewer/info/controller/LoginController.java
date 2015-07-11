package tk.jviewer.info.controller;

import tk.jviewer.info.model.UserProfile;
import tk.jviewer.info.service.SecurityService;

import javax.faces.context.FacesContext;

/**
 * Login controller.
 */
public class LoginController {

    private SecurityService securityService;
    private UserProfile userProfile;

    public void login() {
        if (securityService.authenticate(userProfile.getName(), userProfile.getPassword())) {
            userProfile.setAuthenticated(true);
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("failed", "Ошибка входа. Данные неверны.");
        }
    }

    public void logout() {
        userProfile.setAuthenticated(false);
    }

    //
    // Dependency Injection
    //

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

}
