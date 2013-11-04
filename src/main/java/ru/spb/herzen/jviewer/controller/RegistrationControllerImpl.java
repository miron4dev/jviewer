package ru.spb.herzen.jviewer.controller;

import ru.spb.herzen.jviewer.model.UserModel;
import ru.spb.herzen.jviewer.service.RegistrationService;
import ru.spb.herzen.jviewer.service.ValidationService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationControllerImpl implements RegistrationController, Serializable {

    private UserModel userModel;
    private ValidationService validationService;
    private RegistrationService registrationService;

    @Override
    public String regProfile() {
        if (validationService.checkUser(userModel, "registrationForm")) {
            if (registrationService.regProfile(userModel)) {
                return "index?faces-redirect=true";
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("registrationForm:name", new FacesMessage("User with that name is already exist."));
            }
        }

        return null;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void setValidationService(ValidationService validationService) {
        this.validationService = validationService;
    }
}
