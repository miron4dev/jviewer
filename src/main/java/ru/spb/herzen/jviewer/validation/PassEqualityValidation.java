package ru.spb.herzen.jviewer.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Custom UI validator, which checks password and retryPassword fields.
 * @author Evgeny Mironenko
 */
@FacesValidator("rtPassValidation")
public class PassEqualityValidation implements Validator {

    /**
     * Checks password and retryPassword fields. They should be equals and not empty.
     * @param facesContext current FacesContext
     * @param uiComponent password UI component
     * @param o retryPassword UI object
     */
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) {
        UIInput passwordComponent = (UIInput) uiComponent.getAttributes().get("passwordComponent");
        String password = (String) passwordComponent.getValue();
        String confirmPassword = (String) o;

        if(confirmPassword.isEmpty()){
            FacesMessage msg = new FacesMessage("Field cannot be null");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        if (!confirmPassword.equals(password)) {
            FacesMessage msg = new FacesMessage("Passwords don't match.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
