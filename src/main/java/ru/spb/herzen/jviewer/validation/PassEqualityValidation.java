package ru.spb.herzen.jviewer.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeny
 * Date: 11/30/13
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
@FacesValidator("rtPassValidation")
public class PassEqualityValidation implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
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
