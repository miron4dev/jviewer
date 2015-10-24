package tk.jviewer.dialog;

import org.springframework.dao.DataIntegrityViolationException;
import tk.jviewer.model.LocaleModel;
import tk.jviewer.service.RegistrationService;
import tk.jviewer.service.ResourceService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Serves for "registration" use case.
 */
public class RegistrationDialog {

    private String name;
    private String password;
    private String invitationId;

    private LocaleModel localeModel;
    private RegistrationService registrationService;
    private ResourceService resourceService;

    public String createProfile() {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        try {
            boolean success = registrationService.createProfile(name, password, invitationId);
            if (success) {
                currentInstance.getExternalContext().getFlash().put("success", getResource("J6"));
                return "index?faces-redirect=true";
            }
            currentInstance.addMessage("registrationForm:inviteID", new FacesMessage(getResource("J23")));
        } catch (DataIntegrityViolationException e) {
            currentInstance.addMessage("registrationForm:name", new FacesMessage(getResource("J22")));
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId;
    }

    //
    // Helper methods
    //

    private String getResource(String resourceId) {
        return resourceService.getValue(localeModel.getCurrentLocale(), resourceId);
    }

    //
    // Dependency Injection
    //

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
