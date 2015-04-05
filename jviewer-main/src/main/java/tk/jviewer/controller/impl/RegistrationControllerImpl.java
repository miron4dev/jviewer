package tk.jviewer.controller.impl;

import org.apache.log4j.Logger;
import tk.jviewer.controller.RegistrationController;
import tk.jviewer.messages.RegistrationMsg;
import tk.jviewer.model.impl.LocaleModel;
import tk.jviewer.model.impl.RequestModel;
import tk.jviewer.service.RegistrationService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Registration controller implementation.
 * @author Evgeny Mironenko
 */
public class RegistrationControllerImpl implements RegistrationController, Serializable {

    private static final Logger LOG = Logger.getLogger(RegistrationControllerImpl.class);

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
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        String remoteHost = "Host: " + ((HttpServletRequest)currentInstance.getExternalContext().getRequest()).getRemoteHost();
        if (result == RegistrationMsg.SUCCESS) {
            currentInstance.getExternalContext().getFlash().put("success", localeModel.getLocaleFile().getProperty("registrationSuccessfulMessage"));
            LOG.info(remoteHost +  " | User was registered.");
            return "index?faces-redirect=true";
        } else {
            if (result == RegistrationMsg.EXIST) {
                LOG.warn(remoteHost + " | Registration failed - user is exist.");
                currentInstance.addMessage("registrationForm:name", new FacesMessage(localeModel.getLocaleFile().getProperty("userExistMessage")));
            } else if (result == RegistrationMsg.INVITATION_ID) {
                LOG.warn(remoteHost + " | Registration failed - invitation id is invalid.");
                currentInstance.addMessage("registrationForm:inviteID", new FacesMessage(localeModel.getLocaleFile().getProperty("badInvitationIdMessage")));
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
