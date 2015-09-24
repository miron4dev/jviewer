package tk.jviewer.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import tk.jviewer.dao.LoginDao;
import tk.jviewer.dao.ValidationDao;
import tk.jviewer.profile.UserProfile;
import tk.jviewer.service.impl.LoginServiceImpl;

import static org.easymock.EasyMock.*;

/**
 * @author Evgeny Mironenko
 */
public class LoginServiceTest {

    private LoginServiceImpl loginService;
    private ValidationDao validationDao;
    private LoginDao loginDao;

    @Before
    public void init() {
        loginService = new LoginServiceImpl();
        validationDao = createStrictMock(ValidationDao.class);
        loginDao = createStrictMock(LoginDao.class);
        loginService.setValidationDao(validationDao);
        loginService.setLoginDao(loginDao);
    }

    @After
    public void destroy() {
        loginService = null;
        validationDao = null;
        loginDao = null;
    }

    @Test
    public void testGetData_success() throws Exception {
        UserProfile userProfile = new UserProfile();
        String userName = "Test Test";
        String password = "password1234+";
        expect(validationDao.getUserPassword(userName)).andReturn(password);
        replay(validationDao);
        expect(loginDao.getData(userName)).andReturn(userProfile);
        replay(loginDao);
        Assert.assertEquals(userProfile, loginService.getData(userName, password));
        verify(validationDao);
        verify(loginDao);
    }

    @Test
    public void testGetData_failDao() throws Exception {
        String userName = "Test Test";
        String password = "password1234+";
        expect(validationDao.getUserPassword(userName)).andThrow(new EmptyResultDataAccessException(1));
        replay(validationDao);
        Assert.assertEquals(null, loginService.getData(userName, password));
        verify(validationDao);
    }

    @Test
    public void testGetData_failCompare() throws Exception {
        UserProfile userProfile = new UserProfile();
        String userName = "Test Test";
        String password = "password1234+";
        expect(validationDao.getUserPassword(userName)).andReturn("anotherPassword");
        replay(validationDao);
        Assert.assertNotEquals(userProfile, loginService.getData(userName, password));
        verify(validationDao);
    }

}
