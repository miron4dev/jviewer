package tk.jviewer.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import tk.jviewer.entity.ConfigEntity;
import tk.jviewer.entity.UserEntity;
import tk.jviewer.service.RegistrationService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Registration service implementation.
 * @author Evgeny Mironenko
 */
public class RegistrationServiceImpl implements RegistrationService {

    static final String INVITATION_ID = "invitationID";
    static final String USER_PERMISSIONS = "ROLE_USER";
    static final String ADMIN_PERMISSIONS = "ROLE_ADMIN";

    @PersistenceContext
    private EntityManager em;

    private BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public boolean createProfile(String name, String password, String invitationId) {
        if (!isEmpty(invitationId) && !encoder.matches(invitationId, getInvitationId())) {
            return false;
        }
        String role;
        if (isEmpty(invitationId)) {
            role = USER_PERMISSIONS;
        } else {
            role = ADMIN_PERMISSIONS;
        }
        em.persist(new UserEntity(name, encoder.encode(password), role));
        return true;
    }

    private String getInvitationId() {
        return em.find(ConfigEntity.class, INVITATION_ID).getValue();
    }

    //
    // Setters for Dependency Injection.
    //

    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
}
