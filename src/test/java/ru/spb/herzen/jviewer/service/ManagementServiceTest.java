package ru.spb.herzen.jviewer.service;

import org.apache.shale.test.mock.MockFacesContext;
import org.apache.shale.test.mock.MockHttpServletRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.QueryTimeoutException;
import ru.spb.herzen.jviewer.dao.ManagementDao;
import ru.spb.herzen.jviewer.service.impl.ManagementServiceImpl;
import ru.spb.herzen.jviewer.utils.CommonUtil;

import javax.faces.context.ExternalContext;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Evgeny Mironenko
 */
public class ManagementServiceTest {

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
    public void testCreateRoom_success() throws Exception {
        String room = "TestRoom";
        String password = null;
        expect(managementDao.createRoom(room, password)).andReturn(true);
        replay(managementDao);
        assertTrue(managementService.createRoom(room, password));
        verify(managementDao);
    }

    @Test
     public void testCreateRoom_fail() throws Exception {
        String room = "TestRoom";
        String password = null;
        expect(managementDao.createRoom(room, password)).andThrow(new QueryTimeoutException("Failed"));
        replay(managementDao);
        CommonUtil.replayLogging();
        assertFalse(managementService.createRoom(room, password));
        verify(managementDao);
    }

    @Test
    public void testRemoveRoom_fail() throws Exception {
        String room = "TestRoom";
        expect(managementDao.removeRoom(room)).andThrow(new QueryTimeoutException("Failed"));
        replay(managementDao);
        CommonUtil.replayLogging();
        assertFalse(managementService.removeRoom(room));
        verify(managementDao);
    }
}
