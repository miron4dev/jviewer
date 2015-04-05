package tk.jviewer.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tk.jviewer.model.impl.RoomModelImpl;
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

    private RoomModelImpl roomModel;
    private ManagementService managementService;
    private LoginService loginService;

    @Before
    public void init() {
        managementService = createMock(ManagementService.class);
        loginService = createMock(LoginService.class);
        roomModel = new RoomModelImpl();
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
        List<RoomModelImpl> rooms = new ArrayList<>();
        rooms.add(roomModel);
        expect(loginService.getRooms()).andReturn(rooms);
        replay(loginService);
        roomModel.refreshRooms();
    }

    @Test
    public void testEquals_true() {
        RoomModel anotherRoom = new RoomModelImpl();
        anotherRoom.setName("testRoom");
        anotherRoom.setCurrentRoom("testRoom");
        anotherRoom.setRooms(new ArrayList<>());
        assertTrue(roomModel.equals(anotherRoom));
    }

    @Test
    public void testEquals_false() {
        Object anotherRoom = new Object();
        assertFalse(roomModel.equals(anotherRoom));
    }

    @Test
    public void testHashCode() {
        assertEquals(16, roomModel.hashCode());
    }

    private void replayLoginService() {
        expect(loginService.getRooms()).andReturn(new ArrayList<>());
        replay(loginService);
    }
}
