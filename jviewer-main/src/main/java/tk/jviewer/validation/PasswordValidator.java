package tk.jviewer.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator for password and confirm password fields.
 * @author Evgeny Mironenko
 */
@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

    /**
     * Validates entered password fields. They are should be equal.
     * @param facesContext current instance of the {@link FacesContext}.
     * @param uiComponent confirmPassword UI component.
     * @param value password.
     */
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) {
        String password = value.toString();
        UIInput uiInputConfirmPassword  = (UIInput) uiComponent.getAttributes().get("confirmPassword");
        String confirmPassword = (String) uiInputConfirmPassword.getSubmittedValue();

        if (!confirmPassword.equals(password)) {
            // TODO localization
            FacesMessage msg = new FacesMessage("Password and confirm password fields must be equal!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
