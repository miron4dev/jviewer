package tk.jviewer.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.validator.ValidatorException;

import java.util.Map;

import static org.easymock.EasyMock.*;

/**
 * @author Evgeny Mironenko
 */
public class PasswordValidatorTest {

    private PasswordValidator passwordValidator;
    private UIComponent uiComponent;
    private UIInput passwordComponent;

    @Before
    public void init() {
        passwordValidator = new PasswordValidator();
        uiComponent = createMock(UIComponent.class);
        passwordComponent = createMock(UIInput.class);
    }

    @After
    public void destroy() {
        passwordValidator = null;
        uiComponent = null;
        passwordComponent = null;
    }

    @Test
    public void testValidate_success() throws Exception {
        Map<String, Object> attributes = createMock(Map.class);
        String password = "123456";
        String retypePassword = "123456";
        expect(uiComponent.getAttributes()).andReturn(attributes);
        replay(uiComponent);
        expect(attributes.get("confirmPassword")).andReturn(passwordComponent);
        replay(attributes);
        expect(passwordComponent.getSubmittedValue()).andReturn(password);
        replay(passwordComponent);
        passwordValidator.validate(null, uiComponent, retypePassword);
    }

    @Test(expected = ValidatorException.class)
    public void testValidate_failPasswordEmpty() throws Exception {
        testValidate_failCommon("");
    }

    @Test(expected = ValidatorException.class)
    public void testValidate_failCompare() throws Exception {
        testValidate_failCommon("654321");
    }

    private void testValidate_failCommon(String retypePassword) {
        Map<String, Object> attributes = createMock(Map.class);
        String password = "123456";
        expect(uiComponent.getAttributes()).andReturn(attributes);
        replay(uiComponent);
        expect(attributes.get("confirmPassword")).andReturn(passwordComponent);
        replay(attributes);
        expect(passwordComponent.getSubmittedValue()).andReturn(password);
        replay(passwordComponent);
        passwordValidator.validate(null, uiComponent, retypePassword);
    }
}
