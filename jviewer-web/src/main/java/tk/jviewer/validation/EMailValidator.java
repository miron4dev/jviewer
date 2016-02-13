package tk.jviewer.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Faces validator for the email field.
 *
 * @author Evgeny Mironenko
 */
@FacesValidator("emailValidator")
public class EMailValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = value.toString();

        if (!isValidEmailAddress(email)) {
            // TODO localization
            FacesMessage msg = new FacesMessage("The specified email has not valid format");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    /**
     * Returns true if the specified email value is valid.
     *
     * @param email email for the validation.
     * @return see description.
     */
    private boolean isValidEmailAddress(String email) {
        try {
            InternetAddress address = new InternetAddress(email);
            address.validate();
        } catch (AddressException ex) {
            return false;
        }
        return true;
    }
}
