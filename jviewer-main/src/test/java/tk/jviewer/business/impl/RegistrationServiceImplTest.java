package tk.jviewer.business.impl;

import org.easymock.Capture;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import tk.jviewer.business.model.ConfigEntity;
import tk.jviewer.business.model.UserEntity;
import tk.jviewer.mock.AbstractMockCtrlTestSupport;

import javax.persistence.EntityManager;

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.*;

public class RegistrationServiceImplTest extends AbstractMockCtrlTestSupport {

    private RegistrationServiceImpl service;

    private EntityManager em;
    private BCryptPasswordEncoder encoder;

    @Before
    public void setUp() throws Exception {
        service = new RegistrationServiceImpl();
        encoder = mockCtrl.createMock(BCryptPasswordEncoder.class);
        em = mockCtrl.createMock(EntityManager.class);
        service.setEncoder(encoder);
        ReflectionTestUtils.setField(service, "em", em);
    }

    @Test
    public void testCreateProfile_incorrectInvitationId() throws Exception {
        String name = "testName";
        String password = "testPassword";
        String invitationId = "incorrectInvitationId";
        expect(encoder.matches(invitationId, getInvitationId())).andReturn(false);
        mockCtrl.replay();

        assertFalse(service.createProfile(name, password, invitationId));
    }

    @Test
    public void testCreateProfile_user() throws Exception {
        String name = "testName";
        String password = "testPassword";
        String encodedPassword = "testEncodedPassword";
        expect(encoder.encode(password)).andReturn(encodedPassword);
        Capture<UserEntity> entityCapture = Capture.newInstance();
        em.persist(capture(entityCapture));
        mockCtrl.replay();

        assertTrue(service.createProfile(name, password, null));
        UserEntity userEntity = entityCapture.getValue();
        assertEquals(name, userEntity.getUsername());
        assertEquals(encodedPassword, userEntity.getPassword());
        assertEquals(RegistrationServiceImpl.USER_PERMISSIONS, userEntity.getRole());
    }

    @Test
    public void testCreateProfile_admin() throws Exception {
        String name = "testName";
        String password = "testPassword";
        String encodedPassword = "testEncodedPassword";
        String invitationId = getInvitationId();
        expect(encoder.matches(invitationId, invitationId)).andReturn(true);
        expect(encoder.encode(password)).andReturn(encodedPassword);
        Capture<UserEntity> entityCapture = Capture.newInstance();
        em.persist(capture(entityCapture));
        mockCtrl.replay();

        assertTrue(service.createProfile(name, password, invitationId));
        UserEntity userEntity = entityCapture.getValue();
        assertEquals(name, userEntity.getUsername());
        assertEquals(encodedPassword, userEntity.getPassword());
        assertEquals(RegistrationServiceImpl.ADMIN_PERMISSIONS, userEntity.getRole());
    }

    private String getInvitationId() {
        String invitationId = "testInvitationId";
        ConfigEntity configEntity = mockCtrl.createMock(ConfigEntity.class);
        expect(em.find(ConfigEntity.class, RegistrationServiceImpl.INVITATION_ID)).andReturn(configEntity);
        expect(configEntity.getValue()).andReturn(invitationId);
        return invitationId;
    }
}