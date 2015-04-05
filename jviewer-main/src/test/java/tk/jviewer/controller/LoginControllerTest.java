package tk.jviewer.controller;

import org.apache.shale.test.mock.MockFacesContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import tk.jviewer.controller.impl.LoginControllerImpl;
import tk.jviewer.model.RoomModel;
import tk.jviewer.model.UserModel;
import tk.jviewer.model.impl.UserModelImpl;
import tk.jviewer.testutils.CommonUtil;

import javax.faces.context.ExternalContext;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Evgeny Mironenko
 */
public class LoginControllerTest {

    private LoginControllerImpl loginController;
    private UserModel userModel;
    private RoomModel roomModel;
    private AuthenticationManager authenticationManager;
    private MockFacesContext facesContext;

    @Before
    public void init() throws Exception {
        facesContext = new MockFacesContext();
        loginController = new LoginControllerImpl();
        userModel = new UserModelImpl();
        userModel.setName("testLogin");
        userModel.setPassword("testPassword");
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
    public void testLoginUser_success() throws Exception {
        prepareUserSuccess();
        assertEquals(loginController.loginUser(), "rooms?faces-redirect=true");
        verify(roomModel);
    }

    @Test
    public void testLoginUser_fail() throws Exception {
        prepareUserFail();
        assertEquals(loginController.loginUser(), null);
        verify(authenticationManager);
    }

    @Test
    public void testLoginAdmin_success() throws Exception {
        prepareUserSuccessNonEmptyRoomList();
        assertEquals(loginController.loginAdmin(), "admin?faces-redirect=true");
        verify(roomModel);
    }

    @Test
    public void testLoginAdmin_fail() throws Exception {
        prepareUserFail();
        assertEquals(loginController.loginAdmin(), null);
        verify(authenticationManager);
    }

    @Test
    public void testLogout() throws Exception {
        ExternalContext externalContext = createMock(ExternalContext.class);
        externalContext.invalidateSession();
        expectLastCall();
        replay(externalContext);
        facesContext.setExternalContext(externalContext);
        assertEquals(loginController.logout(), "index?faces-redirect=true");
    }

    @Test
     public void testPageRedirect_success() throws Exception {
        String page = "test";
        expect(roomModel.getCurrentRoom()).andReturn(page).times(3);
        replay(roomModel);
        assertEquals(loginController.pageRedirect(page), page + "?faces-redirect=true");
        verify(roomModel);
    }

    @Test
    public void testPageRedirect_fail() throws Exception {
        String page = "test";
        expect(roomModel.getCurrentRoom()).andReturn(null);
        replay(roomModel);
        assertEquals(loginController.pageRedirect(page), null);
        verify(roomModel);
    }

    private void prepareUserSuccess() throws Exception {
        List<String> rooms = createMock(List.class);
        roomModel.refreshRooms();
        expectLastCall();
        expect(roomModel.getRooms()).andReturn(rooms);
        expect(rooms.isEmpty()).andReturn(true);
        replay(rooms);
        roomModel.setCurrentRoom("");
        expectLastCall();
        replay(roomModel);
    }

    private void prepareUserSuccessNonEmptyRoomList() throws Exception {
        List<String> rooms = createMock(List.class);
        String roomName = "Test room";
        roomModel.refreshRooms();
        expectLastCall();
        expect(roomModel.getRooms()).andReturn(rooms).times(2);
        expect(rooms.isEmpty()).andReturn(false);
        expect(rooms.get(0)).andReturn(roomName);
        replay(rooms);
        roomModel.setCurrentRoom(roomName);
        expectLastCall();
        replay(roomModel);
    }

    private void prepareUserFail() {
        expect(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("testLogin", "testPassword")))
            .andThrow(new BadCredentialsException("Failed login"));
        replay(authenticationManager);
        CommonUtil.replayLogging();
    }

}
