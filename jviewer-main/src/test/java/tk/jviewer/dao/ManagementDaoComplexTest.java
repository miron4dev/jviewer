package tk.jviewer.dao;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Evgeny Mironenko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dao-applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManagementDaoComplexTest {

    @Autowired
    private ManagementDao managementDao;

    private String roomName = "TestRoom";

    @Test
    public void testCreateRoom() throws Exception {
        managementDao.createRoom(roomName, null);
        managementDao.removeRoom(roomName);
    }

    @Test
    public void testRemoveRoom() throws Exception {
        managementDao.createRoom(roomName, null);
        managementDao.removeRoom(roomName);
    }

}
