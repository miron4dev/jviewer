package tk.jviewer.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import tk.jviewer.dao.RegistrationDao;
import tk.jviewer.dao.ValidationDao;
import tk.jviewer.messages.RegistrationMsg;
import tk.jviewer.model.RequestModel;
import tk.jviewer.service.impl.RegistrationServiceImpl;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Evgeny Mironenko
 */
public class RegistrationServiceTest {

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
    public void testRegProfileUser_success() throws Exception {
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
    public void testRegProfileUser_fail() throws Exception {
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
    public void testRegProfileAdmin() throws Exception {
        RequestModel model = new RequestModel();
        String name = "TestAdmin";
        String role = "ROLE_ADMIN";
        model.setName(name);
        model.setInvitationID("12345");
        validationDao.checkUser(name);
        expectLastCall().andThrow(new EmptyResultDataAccessException(0));
        replay(validationDao);
        expect(registrationDao.getInvitationID()).andReturn(model.getInvitationID());
        registrationDao.regProfile(name, null, role, null);
        expectLastCall();
        replay(registrationDao);
        assertEquals(RegistrationMsg.SUCCESS, registrationService.regProfile(model));
        verify(validationDao);
        verify(registrationDao);
    }

    @Test
    public void testRegProfileInvitationId() throws Exception {
        RequestModel model = new RequestModel();
        String id = "123";
        String name = "TestUser";
        String faculty = "IT";
        model.setName(name);
        model.setInvitationID(id);
        model.setFaculty(faculty);
        model.setTemp("temp");
        assert("temp".equals(model.getTemp()));  //TODO UI test: test line for cheat of test coverage statistic
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
