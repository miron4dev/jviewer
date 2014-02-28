package ru.spb.herzen.jviewer.controller;

import ru.spb.herzen.jviewer.messages.RegistrationMsg;
import ru.spb.herzen.jviewer.model.LocaleModel;
import ru.spb.herzen.jviewer.model.RequestModel;
import ru.spb.herzen.jviewer.model.UserModel;
import ru.spb.herzen.jviewer.service.RegistrationService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Registration controller implementation.
 * @author Evgeny Mironenko
 */
public class RegistrationControllerImpl implements RegistrationController, Serializable {

    private RequestModel requestModel;
    private LocaleModel localeModel;
    private RegistrationService registrationService;

    /**
     * Creates new profile.
     * @return URL for redirection, if registration was successful, and null, if registration failed.
     */
    @Override
    public String regProfile() {
        RegistrationMsg result = registrationService.regProfile(requestModel);
        if (result == RegistrationMsg.SUCCESS) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", localeModel.getLocaleFile().getProperty("registrationSuccessfulMessage"));
            return "index?faces-redirect=true";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            if (result == RegistrationMsg.EXIST) {
                context.addMessage("registrationForm:name", new FacesMessage(localeModel.getLocaleFile().getProperty("userExistMessage")));
            } else if (result == RegistrationMsg.INVITATION_ID) {
                context.addMessage("registrationForm:inviteID", new FacesMessage(localeModel.getLocaleFile().getProperty("badInvitationIdMessage")));
            }
            return null;
        }
    }

    //
    // Setters for Dependency Injection.
    //

    public void setRequestModel(RequestModel requestModel) {
        this.requestModel = requestModel;
    }

    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void setLocaleModel(LocaleModel localeModel) {
        this.localeModel = localeModel;
    }
}
