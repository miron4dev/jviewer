package tk.jviewer.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import tk.jviewer.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Registration service implementation.
 * @author Evgeny Mironenko
 */
public class RegistrationServiceImpl implements RegistrationService {

    static final String USER_PERMISSIONS = "ROLE_USER";
    static final String ADMIN_PERMISSIONS = "ROLE_ADMIN";

    @PersistenceContext
    private EntityManager em;

    private BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public void createProfile(String name, String email, String password) {
        em.persist(new UserEntity(name, encoder.encode(password), USER_PERMISSIONS));
    }

    //
    // Setters for Dependency Injection.
    //

    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
}
