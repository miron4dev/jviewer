package ru.spb.herzen.jviewer.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.spb.herzen.jviewer.dao.ManagementDao;
import ru.spb.herzen.jviewer.service.impl.ManagementServiceImpl;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertTrue;

/**
 * @author Evgeny Mironenko
 */
public class ManagementServiceImplTest {

    private ManagementServiceImpl managementService;
    private ManagementDao managementDao;

    @Before
    public void init() {
        managementService = new ManagementServiceImpl();
        managementDao = createStrictMock(ManagementDao.class);
        managementService.setManagementDao(managementDao);
    }

    @After
    public void destroy() {
        managementService = null;
        managementDao = null;
    }

    @Test
    public void testCreateRoom() throws Exception {
        String room = "TestRoom";
        String password = null;
        expect(managementDao.createRoom(room, password)).andReturn(true);
        replay(managementDao);
        assertTrue(managementService.createRoom(room, password));
        verify(managementDao);
    }

    @Test
    public void testRemoveRoom() throws Exception {
        String room = "TestRoom";
        expect(managementDao.removeRoom(room)).andReturn(true);
        replay(managementDao);
        assertTrue(managementService.removeRoom(room));
        verify(managementDao);
    }
}
