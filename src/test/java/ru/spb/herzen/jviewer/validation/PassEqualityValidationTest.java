package ru.spb.herzen.jviewer.validation;

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
public class PassEqualityValidationTest {

    private PassEqualityValidation passEqualityValidation;
    private UIComponent uiComponent;
    private UIInput passwordComponent;

    @Before
    public void init() {
        passEqualityValidation = new PassEqualityValidation();
        uiComponent = createMock(UIComponent.class);
        passwordComponent = createMock(UIInput.class);
    }

    @After
    public void destroy() {
        passEqualityValidation = null;
        uiComponent = null;
        passwordComponent = null;
    }

    @Test
    public void testValidateSuccess() throws Exception
    {
        Map<String, Object> attributes = createMock(Map.class);
        String password = "123456";
        String retypePassword = "123456";
        expect(uiComponent.getAttributes()).andReturn(attributes);
        replay(uiComponent);
        expect(attributes.get("passwordComponent")).andReturn(passwordComponent);
        replay(attributes);
        expect(passwordComponent.getValue()).andReturn(password);
        replay(passwordComponent);
        passEqualityValidation.validate(null, uiComponent, retypePassword);
    }

    @Test(expected = ValidatorException.class)
    public void testValidateFail() throws Exception {
        Map<String, Object> attributes = createMock(Map.class);
        String password = "123456";
        String retypePassword = "654321";
        expect(uiComponent.getAttributes()).andReturn(attributes);
        replay(uiComponent);
        expect(attributes.get("passwordComponent")).andReturn(passwordComponent);
        replay(attributes);
        expect(passwordComponent.getValue()).andReturn(password);
        replay(passwordComponent);
        passEqualityValidation.validate(null, uiComponent, retypePassword);
    }
}
