package ru.spb.herzen.jviewer.controller;

import ru.spb.herzen.jviewer.model.UserModel;
import ru.spb.herzen.jviewer.service.LoginService;
import ru.spb.herzen.jviewer.service.ValidationService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 5:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginControllerImpl implements LoginController{

    private UserModel userModel;
    private ValidationService validationService;
    private LoginService loginService;

    @Override
    public String login() {
        if(validationService.checkUser(userModel, "loginForm")){
            UserModel user = loginService.getData(userModel);
            if(user == null){
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("loginForm:name", new FacesMessage("Invalid data or user is not exist."));
                return null;
            }
            else {
                user.setOnline(true);
                if(user.getRole().equals("user")){
                    return "main.xhtml?faces-redirect=true";
                }
                else if(user.getRole().equals("admin")){
                    return "display.xhtml?faces-redirect=true";
                }
                else throw new RuntimeException("Hack attack to LoginController");
            }
        }

        return null;
    }

    @Override
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        userModel.setOnline(false);

        return "index?faces-redirect=true";
    }

    public void setValidationService(ValidationService validationService) {
        this.validationService = validationService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
