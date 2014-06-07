package ru.spb.herzen.jviewer.controller;

import org.apache.shale.test.mock.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.spb.herzen.jviewer.controller.impl.RegistrationControllerImpl;
import ru.spb.herzen.jviewer.messages.RegistrationMsg;
import ru.spb.herzen.jviewer.model.impl.LocaleModel;
import ru.spb.herzen.jviewer.model.impl.RequestModel;
import ru.spb.herzen.jviewer.service.RegistrationService;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import java.util.Properties;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Evgeny Mironenko
 */
public class RegistrationControllerTest {

    private RegistrationControllerImpl registrationController;
    private RequestModel requestModel;
    private RegistrationService registrationService;
    private LocaleModel localeModel;
    private MockFacesContext facesContext;
    private ExternalContext externalContext;

    @Before
    public void init() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        facesContext = new MockFacesContext();
        registrationController = new RegistrationControllerImpl();
        requestModel = new RequestModel();
        localeModel = createMock(LocaleModel.class);
        registrationService = createStrictMock(RegistrationService.class);
        externalContext = createMock(ExternalContext.class);
        facesContext.setExternalContext(externalContext);
        registrationController.setRequestModel(requestModel);
        registrationController.setLocaleModel(localeModel);
        registrationController.setRegistrationService(registrationService);
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
        Flash flash = createMock(Flash.class);
        expect(externalContext.getFlash()).andReturn(flash);
        replay(externalContext);
        expect(registrationService.regProfile(requestModel)).andReturn(RegistrationMsg.SUCCESS);
        replay(registrationService);
        expect(localeModel.getLocaleFile()).andReturn(new Properties());
        replay(localeModel);
        assertEquals(registrationController.regProfile(), "index?faces-redirect=true");
        verify(registrationService);
    }

    @Test
    public void testRegProfile_failUserExist() throws Exception {
        testRegProfileFail(RegistrationMsg.EXIST);
    }

    @Test
    public void testRegProfile_failWrongId() throws Exception {
        testRegProfileFail(RegistrationMsg.INVITATION_ID);
    }

    private void testRegProfileFail(RegistrationMsg result) {
        expect(registrationService.regProfile(requestModel)).andReturn(result);
        replay(registrationService);
        expect(localeModel.getLocaleFile()).andReturn(new Properties());
        replay(localeModel);
        replay(externalContext);
        assertEquals(registrationController.regProfile(), null);
        verify(registrationService);
    }

}
