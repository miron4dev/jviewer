package ru.spb.herzen.jviewer.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.AuthenticationManager;
import ru.spb.herzen.jviewer.common.MockFacesContext;
import ru.spb.herzen.jviewer.controller.impl.LoginControllerImpl;
import ru.spb.herzen.jviewer.model.RoomModel;
import ru.spb.herzen.jviewer.model.UserModel;
import ru.spb.herzen.jviewer.model.impl.UserModelImpl;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Evgeny Mironenko
 */
public class LoginControllerImplTest {

    private LoginControllerImpl loginController;
    private UserModel userModel;
    private RoomModel roomModel;
    private AuthenticationManager authenticationManager;
    private FacesContext facesContext;

    @Before
    public void init() throws Exception {
        loginController = new LoginControllerImpl();
        userModel = new UserModelImpl();
        roomModel = createStrictMock(RoomModel.class);
        authenticationManager = createStrictMock(AuthenticationManager.class);
        loginController.setUserModel(userModel);
        loginController.setRoomModel(roomModel);
        loginController.setAuthenticationManager(authenticationManager);
    }

    @After
    public void destroy() throws Exception {
        loginController = null;
        userModel = null;
        roomModel = null;
        authenticationManager = null;
        facesContext = null;
    }

    @Test
    public void testLoginUser() throws Exception {
        prepareUser();
        assertEquals(loginController.loginUser(), "rooms?faces-redirect=true");
        verify(roomModel);
    }

    @Test
    public void testLoginAdmin() throws Exception {
        prepareUser();
        assertEquals(loginController.loginAdmin(), "admin?faces-redirect=true");
        verify(roomModel);
    }

    @Test
    public void testLogout() throws Exception {
        ExternalContext externalContext = createMock(ExternalContext.class);
        facesContext = MockFacesContext.createStrictMock();
        expect(facesContext.getExternalContext()).andReturn(externalContext);
        replay(facesContext);
        externalContext.invalidateSession();
        expectLastCall();
        replay(externalContext);
        assertEquals(loginController.logout(), "index?faces-redirect=true");
        verify(facesContext);
    }

    @Test
    public void testPageRedirect() throws Exception {
        String page = "test";
        expect(roomModel.getCurrentRoom()).andReturn(page).times(3);
        replay(roomModel);
        assertEquals(loginController.pageRedirect(page), page + "?faces-redirect=true");
        verify(roomModel);
    }

    private void prepareUser() throws Exception {
        List<String> rooms = createMock(List.class);
        roomModel.refreshRooms();
        expectLastCall();
        expect(roomModel.getRooms()).andReturn(rooms);
        expect(rooms.size()).andReturn(0);
        replay(rooms);
        roomModel.setCurrentRoom("");
        expectLastCall();
        replay(roomModel);
    }

}
