package tk.jviewer.controller.impl;

import org.apache.log4j.Logger;
import tk.jviewer.controller.RegistrationController;
import tk.jviewer.messages.RegistrationMsg;
import tk.jviewer.model.LocaleModel;
import tk.jviewer.model.RequestModel;
import tk.jviewer.service.RegistrationService;
import tk.jviewer.service.ResourceService;

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
    private ResourceService resourceService;

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
            currentInstance.getExternalContext().getFlash().put("success", resourceService.getValue(localeModel.getCurrentLocale(),
                    "J6"));
            LOG.info(remoteHost +  " | User was registered.");
            return "index?faces-redirect=true";
        } else {
            if (result == RegistrationMsg.EXIST) {
                LOG.warn(remoteHost + " | Registration failed - user is exist.");
                currentInstance.addMessage("registrationForm:name", new FacesMessage(resourceService.getValue(localeModel.getCurrentLocale(),
                        "J22")));
            } else if (result == RegistrationMsg.INVITATION_ID) {
                LOG.warn(remoteHost + " | Registration failed - invitation id is invalid.");
                currentInstance.addMessage("registrationForm:inviteID", new FacesMessage(resourceService.getValue(localeModel.getCurrentLocale(),
                        "J23")));
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

    public void setLocaleModel(LocaleModel localeModel) {
        this.localeModel = localeModel;
    }

    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

}
