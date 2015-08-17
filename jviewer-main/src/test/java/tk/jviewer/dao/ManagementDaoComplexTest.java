package tk.jviewer.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Evgeny Mironenko
 */

@Ignore
public class ManagementDaoComplexTest {

    @Autowired
    private ManagementDao managementDao;

    private String roomName = "TestRoom";

    @Test
    public void testCreateRoom() throws Exception {
        managementDao.createRoom(roomName, null);
        managementDao.deleteRoom(roomName);
    }

    @Test
    public void testRemoveRoom() throws Exception {
        managementDao.createRoom(roomName, null);
        managementDao.deleteRoom(roomName);
    }

}
