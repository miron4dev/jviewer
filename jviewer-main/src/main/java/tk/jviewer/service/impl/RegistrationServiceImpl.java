package tk.jviewer.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
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

    private static final String USER_PERMISSIONS = "ROLE_USER";
    private static final String ADMIN_PERMISSIONS = "ROLE_ADMIN";
    //TODO: fix
    private static final String TEMPORARY_INVITATION_ID = "$2a$11$xHcnk0MN5oZ9ROJIUlWmW.HNyMj5pu.slIvs4oISWhvw7ijHP0nL2";

    @PersistenceContext
    private EntityManager em;

    private BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public boolean createProfile(String name, String password, String invitationId) {
        if (!isEmpty(invitationId) && !encoder.matches(invitationId, TEMPORARY_INVITATION_ID)) {
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

    //
    // Setters for Dependency Injection.
    //

    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
}
