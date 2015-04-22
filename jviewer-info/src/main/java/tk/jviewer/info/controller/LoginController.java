package tk.jviewer.info.controller;

import tk.jviewer.info.service.SecurityService;
import tk.jviewer.info.model.UserModel;

import javax.faces.context.FacesContext;

/**
 * Login controller.
 */
public class LoginController {

    private SecurityService securityService;
    private UserModel userModel;

    public void login() {
        if (securityService.authenticate(userModel.getName(), userModel.getPassword())) {
            userModel.setAuthenticated(true);
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("failed", "Ошибка входа. Данные неверны.");
        }
    }

    public void logout() {
        userModel.setAuthenticated(false);
    }

    //
    // Dependency Injection
    //

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

}
