package tk.jviewer.info.controller;

import tk.jviewer.info.manager.SecurityManager;
import tk.jviewer.info.model.UserModel;

import javax.faces.context.FacesContext;

/**
 * Login controller.
 */
public class LoginController {

    private tk.jviewer.info.manager.SecurityManager securityManager;
    private UserModel userModel;

    public void login() {
        if (securityManager.authenticate(userModel.getName(), userModel.getPassword())) {
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

    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

}
