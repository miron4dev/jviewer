package tk.jviewer.controller;

import org.apache.shale.test.mock.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tk.jviewer.controller.impl.RegistrationControllerImpl;
import tk.jviewer.messages.RegistrationMsg;
import tk.jviewer.mock.AbstractMockCtrlTestSupport;
import tk.jviewer.model.LocaleModel;
import tk.jviewer.model.RequestModel;
import tk.jviewer.service.RegistrationService;
import tk.jviewer.service.ResourceService;

import javax.faces.context.ExternalContext;
import javax.faces.context.Flash;

import java.util.Locale;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Evgeny Mironenko
 */
public class RegistrationControllerTest extends AbstractMockCtrlTestSupport {

    private RegistrationControllerImpl registrationController;
    private RequestModel requestModel;
    private RegistrationService registrationService;
    private ResourceService resourceService;
    private LocaleModel localeModel;
    private MockFacesContext facesContext;
    private ExternalContext externalContext;

    @Before
    public void init() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        facesContext = new MockFacesContext();
        registrationController = new RegistrationControllerImpl();
        requestModel = new RequestModel();
        localeModel = mockCtrl.createMock(LocaleModel.class);
        registrationService = mockCtrl.createMock(RegistrationService.class);
        resourceService = mockCtrl.createMock(ResourceService.class);
        externalContext = mockCtrl.createMock(ExternalContext.class);

        facesContext.setExternalContext(externalContext);
        registrationController.setRequestModel(requestModel);
        registrationController.setLocaleModel(localeModel);
        registrationController.setRegistrationService(registrationService);
        registrationController.setResourceService(resourceService);
        expect(externalContext.getRequest()).andReturn(request);
    }

    @After
    public void destroy() throws Exception {
        facesContext = null;
        registrationController = null;
        requestModel = null;
        registrationService = null;
        localeModel = null;
    }

    @Test
    public void testRegProfile_success() throws Exception {
        Locale locale = Locale.ENGLISH;
        String value = "testValue";
        Flash flash = mockCtrl.createMock(Flash.class);
        expect(externalContext.getFlash()).andReturn(flash);
        expect(registrationService.regProfile(requestModel)).andReturn(RegistrationMsg.SUCCESS);
        expect(localeModel.getCurrentLocale()).andReturn(locale);
        expect(resourceService.getValue(locale, "J6")).andReturn(value);
        expect(flash.put("success", value)).andReturn(null);

        mockCtrl.replay();
        assertEquals(registrationController.regProfile(), "index?faces-redirect=true");
    }

    @Test
    public void testRegProfile_failUserExist() throws Exception {
        Locale locale = Locale.ENGLISH;
        expect(registrationService.regProfile(requestModel)).andReturn(RegistrationMsg.EXIST);
        expect(localeModel.getCurrentLocale()).andReturn(locale);
        expect(resourceService.getValue(locale, "J22")).andReturn("testValue");

        mockCtrl.replay();
        assertEquals(registrationController.regProfile(), null);
    }

    @Test
    public void testRegProfile_failWrongId() throws Exception {
        Locale locale = Locale.ENGLISH;
        expect(registrationService.regProfile(requestModel)).andReturn(RegistrationMsg.INVITATION_ID);
        expect(localeModel.getCurrentLocale()).andReturn(locale);
        expect(resourceService.getValue(locale, "J23")).andReturn("testValue");

        mockCtrl.replay();
        assertEquals(registrationController.regProfile(), null);
    }

}
