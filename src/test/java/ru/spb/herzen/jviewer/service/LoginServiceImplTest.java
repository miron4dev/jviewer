package ru.spb.herzen.jviewer.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.spb.herzen.jviewer.dao.LoginDao;
import ru.spb.herzen.jviewer.dao.ValidationDao;
import ru.spb.herzen.jviewer.model.UserModel;
import ru.spb.herzen.jviewer.model.impl.RequestModel;
import ru.spb.herzen.jviewer.model.impl.RoomModelImpl;
import ru.spb.herzen.jviewer.model.impl.UserModelImpl;
import ru.spb.herzen.jviewer.service.impl.LoginServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * @author Evgeny Mironenko
 */
public class LoginServiceImplTest {

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
    public void testGetDataSuccess() throws Exception {
        UserModel userModel = new UserModelImpl();
        RequestModel requestModel = new RequestModel();
        String userName = "Test Test";
        String password = "password1234+";
        requestModel.setName(userName);
        requestModel.setPassword(password);
        expect(validationDao.getUserPassword(userName)).andReturn(password);
        replay(validationDao);
        expect(loginDao.getData(userName)).andReturn(userModel);
        replay(loginDao);
        assertEquals(userModel, loginService.getData(requestModel));
        verify(validationDao);
        verify(loginDao);
    }

    @Test
    public void testGetDataFail() throws Exception {
        RequestModel requestModel = new RequestModel();
        String userName = "Test Test";
        String password = "password1234+";
        requestModel.setName(userName);
        requestModel.setPassword(password);
        expect(validationDao.getUserPassword(userName)).andThrow(new EmptyResultDataAccessException(1));
        replay(validationDao);
        assertEquals(null, loginService.getData(requestModel));
        verify(validationDao);
    }

    @Test
    public void testGetRoomsSuccess() throws Exception {
        List<RoomModelImpl> roomModelList = new ArrayList<>();
        expect(loginDao.getRooms()).andReturn(roomModelList);
        replay(loginDao);
        assertEquals(roomModelList, loginService.getRooms());
        verify(loginDao);
    }

}
