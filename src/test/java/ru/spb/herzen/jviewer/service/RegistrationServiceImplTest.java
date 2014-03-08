package ru.spb.herzen.jviewer.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.spb.herzen.jviewer.dao.RegistrationDao;
import ru.spb.herzen.jviewer.dao.ValidationDao;
import ru.spb.herzen.jviewer.messages.RegistrationMsg;
import ru.spb.herzen.jviewer.model.impl.RequestModel;
import ru.spb.herzen.jviewer.service.impl.RegistrationServiceImpl;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Evgeny Mironenko
 */
public class RegistrationServiceImplTest {

    private RegistrationServiceImpl registrationService;
    private ValidationDao validationDao;
    private RegistrationDao registrationDao;

    @Before
    public void init() throws Exception {
        registrationService = new RegistrationServiceImpl();
        validationDao = createStrictMock(ValidationDao.class);
        registrationDao = createStrictMock(RegistrationDao.class);
        registrationService.setValidationDao(validationDao);
        registrationService.setRegistrationDao(registrationDao);
    }

    @After
    public void destroy() throws Exception {
        registrationService = null;
        validationDao = null;
        registrationDao = null;
    }

    @Test
    public void testRegProfileSuccess() throws Exception {
        RequestModel model = new RequestModel();
        String name = "TestUser";
        String role = "ROLE_USER";
        model.setName(name);
        model.setInvitationID("");
        validationDao.checkUser(name);
        expectLastCall().andThrow(new EmptyResultDataAccessException(0));
        replay(validationDao);
        registrationDao.regProfile(name, null, role, null);
        expectLastCall();
        replay(registrationDao);
        assertEquals(RegistrationMsg.SUCCESS, registrationService.regProfile(model));
        verify(validationDao);
        verify(registrationDao);
    }

    @Test
    public void testRegProfileFailed() throws Exception {
        RequestModel model = new RequestModel();
        String name = "TestUser";
        model.setName(name);
        model.setInvitationID("");
        validationDao.checkUser(name);
        replay(validationDao);
        assertEquals(RegistrationMsg.EXIST, registrationService.regProfile(model));
        verify(validationDao);
    }

    @Test
    public void testRegProfileInvitationId() throws Exception {
        RequestModel model = new RequestModel();
        String name = "TestUser";
        String id = "123";
        model.setName(name);
        model.setInvitationID(id);
        validationDao.checkUser(name);
        expectLastCall().andThrow(new EmptyResultDataAccessException(0));
        replay(validationDao);
        expect(registrationDao.getInvitationID()).andReturn("321");
        replay(registrationDao);
        assertEquals(RegistrationMsg.INVITATION_ID, registrationService.regProfile(model));
        verify(validationDao);
        verify(registrationDao);
    }
}
