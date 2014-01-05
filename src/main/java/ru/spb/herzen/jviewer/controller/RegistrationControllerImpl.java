package ru.spb.herzen.jviewer.controller;

import ru.spb.herzen.jviewer.messages.RegistrationMsg;
import ru.spb.herzen.jviewer.model.RequestModel;
import ru.spb.herzen.jviewer.model.UserModel;
import ru.spb.herzen.jviewer.service.RegistrationService;

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

    private RequestModel requestModel;
    private RegistrationService registrationService;

    @Override
    public String regProfile() {
        RegistrationMsg result = registrationService.regProfile(requestModel);
        if (result == RegistrationMsg.SUCCESS) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "Registration was successful, " +
                    "now you can login.");
            return "index?faces-redirect=true";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            if (result == RegistrationMsg.EXIST) {
                context.addMessage("registrationForm:name", new FacesMessage("User with that name is already exist."));
            } else if (result == RegistrationMsg.INVITATION_ID) {
                context.addMessage("registrationForm:inviteID", new FacesMessage("Invitation ID is wrong."));
            }
            return null;
        }
    }

    public void setRequestModel(RequestModel requestModel) {
        this.requestModel = requestModel;
    }

    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
}
