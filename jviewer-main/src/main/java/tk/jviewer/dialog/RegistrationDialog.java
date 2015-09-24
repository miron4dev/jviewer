package tk.jviewer.dialog;

import tk.jviewer.messages.RegistrationMsg;
import tk.jviewer.model.LocaleModel;
import tk.jviewer.service.RegistrationService;
import tk.jviewer.service.ResourceService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import static tk.jviewer.messages.RegistrationMsg.*;

/**
 * Serves for "registration" use case.
 */
public class RegistrationDialog {

    private String name;
    private String password;
    private String invitationId;
    private String department;

    private LocaleModel localeModel;
    private RegistrationService registrationService;
    private ResourceService resourceService;

    public String createProfile() {
        RegistrationMsg result = registrationService.createProfile(name, password, invitationId, department);
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        if (result == SUCCESS) {
            currentInstance.getExternalContext().getFlash().put("success", getResource("J6"));
            return "index?faces-redirect=true";
        } else {
            if (result == EXIST) {
                currentInstance.addMessage("registrationForm:name", new FacesMessage(getResource("J22")));
            } else if (result == INVITATION_ID) {
                currentInstance.addMessage("registrationForm:inviteID", new FacesMessage(getResource("J23")));
            }
            return null;
        }
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
