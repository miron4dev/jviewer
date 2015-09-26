package tk.jviewer.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import tk.jviewer.dao.RegistrationDao;
import tk.jviewer.dao.ValidationDao;
import tk.jviewer.messages.RegistrationMsg;
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
        String name = "TestUser";
        String password = "TestPassword";
        String invitationId = "";
        String department = "TestDepartment";
        String role = "ROLE_USER";
        validationDao.checkUser(name);
        expectLastCall().andThrow(new EmptyResultDataAccessException(0));
        replay(validationDao);
        registrationDao.createProfile(name, password, role, department);
        expectLastCall();
        replay(registrationDao);
        assertEquals(RegistrationMsg.SUCCESS, registrationService.createProfile(name, password, invitationId, department));
        verify(validationDao);
        verify(registrationDao);
    }

    @Test
    public void testRegProfileUser_fail() throws Exception {
        String name = "TestUser";
        String password = "TestPassword";
        String invitationId = "";
        String department = "TestDepartment";
        validationDao.checkUser(name);
        replay(validationDao);
        assertEquals(RegistrationMsg.EXIST, registrationService.createProfile(name, password, invitationId, department));
        verify(validationDao);
    }

    @Test
    public void testRegProfileAdmin() throws Exception {
        String name = "TestAdmin";
        String password = "TestPassword";
        String invitationId = "12345";
        String department = "TestDepartment";
        String role = "ROLE_ADMIN";
        validationDao.checkUser(name);
        expectLastCall().andThrow(new EmptyResultDataAccessException(0));
        replay(validationDao);
        expect(registrationDao.getInvitationID()).andReturn(invitationId);
        registrationDao.createProfile(name, password, role, department);
        expectLastCall();
        replay(registrationDao);
        assertEquals(RegistrationMsg.SUCCESS, registrationService.createProfile(name, password, invitationId, department));
        verify(validationDao);
        verify(registrationDao);
    }

    @Test
    public void testRegProfileInvitationId() throws Exception {
        String name = "TestAdmin";
        String password = "TestPassword";
        String invitationId = "12345";
        String department = "TestDepartment";
        validationDao.checkUser(name);
        expectLastCall().andThrow(new EmptyResultDataAccessException(0));
        replay(validationDao);
        expect(registrationDao.getInvitationID()).andReturn("321");
        replay(registrationDao);
        assertEquals(RegistrationMsg.INVITATION_ID, registrationService.createProfile(name, password, invitationId, department));
        verify(validationDao);
        verify(registrationDao);
    }
}
