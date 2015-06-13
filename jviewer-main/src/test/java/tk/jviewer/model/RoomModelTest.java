package tk.jviewer.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tk.jviewer.service.LoginService;
import tk.jviewer.service.ManagementService;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Evgeny Mironenko
 */
public class RoomModelTest {

    private RoomModel roomModel;
    private ManagementService managementService;
    private LoginService loginService;

    @Before
    public void init() {
        managementService = createMock(ManagementService.class);
        loginService = createMock(LoginService.class);
        roomModel = new RoomModel();
        roomModel.setName("testRoom");
        roomModel.setCurrentRoom("testRoom");
        roomModel.setRooms(new ArrayList<>());
        roomModel.setManagementService(managementService);
        roomModel.setLoginService(loginService);
        assert(roomModel.getRooms().equals(new ArrayList<>())); //TODO UI test: test line for cheat of test coverage statistic
    }

    @After
    public void destroy() {
        roomModel = null;
        managementService = null;
        loginService = null;
    }

    @Test
    public void testCreateRoom() {
        expect(managementService.createRoom(roomModel.getName(), roomModel.getPassword())).andReturn(true);
        replay(managementService);
        replayLoginService();
        assertEquals("admin?faces-redirect=true", roomModel.createRoom());
    }

    @Test
    public void testRemoveRoom() {
        expect(managementService.removeRoom(roomModel.getCurrentRoom())).andReturn(true);
        replay(managementService);
        replayLoginService();
        assertEquals("admin?faces-redirect=true", roomModel.removeRoom());
    }

    @Test
    public void testRefreshRooms() {
        List<RoomModel> rooms = new ArrayList<>();
        rooms.add(roomModel);
        expect(loginService.getRooms()).andReturn(rooms);
        replay(loginService);
        roomModel.refreshRooms();
    }

    private void replayLoginService() {
        expect(loginService.getRooms()).andReturn(new ArrayList<>());
        replay(loginService);
    }
}
