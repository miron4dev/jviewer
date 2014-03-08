package ru.spb.herzen.jviewer.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.spb.herzen.jviewer.common.MockFacesContext;
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
public class RegistrationControllerImplTest {

    private RegistrationControllerImpl registrationController;
    private RequestModel requestModel;
    private RegistrationService registrationService;
    private LocaleModel localeModel;
    private FacesContext facesContext;

    @Before
    public void init() throws Exception {
        registrationController = new RegistrationControllerImpl();
        requestModel = new RequestModel();
        localeModel = createMock(LocaleModel.class);
        registrationService = createStrictMock(RegistrationService.class);
        registrationController.setRequestModel(requestModel);
        registrationController.setLocaleModel(localeModel);
        registrationController.setRegistrationService(registrationService);
    }

    @After
    public void destroy() throws Exception {
        registrationController = null;
        requestModel = null;
        registrationService = null;
        localeModel = null;
        facesContext = null;
    }

    @Test
    public void testRegProfileSuccess() throws Exception {
        FacesContext facesContext = null;
        ExternalContext externalContext = createMock(ExternalContext.class);
        Flash flash = createMock(Flash.class);
        Properties properties = createMock(Properties.class);
        expect(registrationService.regProfile(requestModel)).andReturn(RegistrationMsg.SUCCESS);
        replay(registrationService);
        facesContext = MockFacesContext.createMock();
        expect(facesContext.getExternalContext()).andReturn(externalContext);
        replay(facesContext);
        expect(externalContext.getFlash()).andReturn(flash);
        replay(externalContext);
        expect(localeModel.getLocaleFile()).andReturn(properties);
        replay(localeModel);
        expect(properties.getProperty("registrationSuccessfulMessage")).andReturn(null);
        replay(properties);
        expect(flash.put("success", null)).andReturn("smth");
        replay(flash);
        assertEquals(registrationController.regProfile(), "index?faces-redirect=true");
        verify(registrationService);
    }

    @Test
    public void testRegProfileFailed() throws Exception {
        expect(registrationService.regProfile(requestModel)).andReturn(null);
        replay(registrationService);
        facesContext = MockFacesContext.createMock();
        assertEquals(registrationController.regProfile(), null);
        verify(registrationService);
    }

}
