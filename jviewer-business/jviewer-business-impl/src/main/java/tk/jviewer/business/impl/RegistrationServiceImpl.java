package tk.jviewer.business.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import tk.jviewer.business.api.RegistrationService;
import tk.jviewer.business.model.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Registration service implementation.
 * @author Evgeny Mironenko
 */
public class RegistrationServiceImpl implements RegistrationService, Serializable {

    private static final long serialVersionUID = -4988532465845828054L;

    @PersistenceContext
    private EntityManager em;

    private BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public void createProfile(String name, String email, String password) {
        em.persist(new UserEntity(name, email, encoder.encode(password)));
    }

    //
    // Setters for Dependency Injection.
    //

    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
}
