package ru.spb.herzen.jviewer.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.spb.herzen.jviewer.model.UserModel;
import ru.spb.herzen.jviewer.model.impl.RoomModelImpl;
import ru.spb.herzen.jviewer.model.impl.UserModelImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Evgeny Mironenko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dao-applicationContext.xml")
public class LoginDaoImplTest {

    @Autowired
    private LoginDao loginDao;

    @Test
    public void testGetDataSuccess() throws Exception {
        UserModel userModel = new UserModelImpl();
        userModel.setId(1);
        userModel.setName("Evgeny Mironenko");
        userModel.setPassword("qwerty");
        userModel.setRole("ROLE_USER");
        userModel.setFaculty("Faculty of Information Technology");
        userModel.setEnabled(true);
        assertEquals(loginDao.getData("Evgeny Mironenko"), userModel);
    }

    @Test
    public void testGetDataFailed() throws Exception {
        UserModel userModel = new UserModelImpl();
        userModel.setId(999);
        assertNotEquals(loginDao.getData("Evgeny Mironenko"), userModel);
    }

    @Test
    public void testGetRooms() throws Exception {
        List<RoomModelImpl> rooms = new ArrayList<>();
        RoomModelImpl roomModel = new RoomModelImpl();
        roomModel.setId(1);
        roomModel.setName("Main");
        roomModel.setPassword(null);
        rooms.add(roomModel);
        assertEquals(loginDao.getRooms(), rooms);
    }
}
