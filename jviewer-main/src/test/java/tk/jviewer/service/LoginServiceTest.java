package tk.jviewer.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import tk.jviewer.dao.LoginDao;
import tk.jviewer.dao.ValidationDao;
import tk.jviewer.model.UserModel;
import tk.jviewer.model.RequestModel;
import tk.jviewer.model.RoomModel;
import tk.jviewer.service.impl.LoginServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

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
        UserModel userModel = new UserModel();
        RequestModel requestModel = new RequestModel();
        String userName = "Test Test";
        String password = "password1234+";
        requestModel.setName(userName);
        requestModel.setPassword(password);
        expect(validationDao.getUserPassword(userName)).andReturn(password);
        replay(validationDao);
        expect(loginDao.getData(userName)).andReturn(userModel);
        replay(loginDao);
        Assert.assertEquals(userModel, loginService.getData(requestModel));
        verify(validationDao);
        verify(loginDao);
    }

    @Test
    public void testGetData_failDao() throws Exception {
        RequestModel requestModel = new RequestModel();
        String userName = "Test Test";
        String password = "password1234+";
        requestModel.setName(userName);
        requestModel.setPassword(password);
        expect(validationDao.getUserPassword(userName)).andThrow(new EmptyResultDataAccessException(1));
        replay(validationDao);
        Assert.assertEquals(null, loginService.getData(requestModel));
        verify(validationDao);
    }

    @Test
    public void testGetData_failCompare() throws Exception {
        UserModel userModel = new UserModel();
        RequestModel requestModel = new RequestModel();
        String userName = "Test Test";
        String password = "password1234+";
        requestModel.setName(userName);
        requestModel.setPassword(password);
        expect(validationDao.getUserPassword(userName)).andReturn("anotherPassword");
        replay(validationDao);
        Assert.assertNotEquals(userModel, loginService.getData(requestModel));
        verify(validationDao);
    }

    @Test
    public void testGetRooms_success() throws Exception {
        List<RoomModel> roomModelList = new ArrayList<>();
        expect(loginDao.getRooms()).andReturn(roomModelList);
        replay(loginDao);
        assertEquals(roomModelList, loginService.getRooms());
        verify(loginDao);
    }

}
