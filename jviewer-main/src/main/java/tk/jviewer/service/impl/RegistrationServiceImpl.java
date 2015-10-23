package tk.jviewer.service.impl;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.jviewer.entity.UserEntity;
import tk.jviewer.messages.RegistrationMsg;
import tk.jviewer.repository.UserRepository;
import tk.jviewer.service.RegistrationService;

import javax.persistence.PersistenceException;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static tk.jviewer.messages.RegistrationMsg.*;

/**
 * Registration service implementation.
 * @author Evgeny Mironenko
 */
public class RegistrationServiceImpl implements RegistrationService {

    private static Logger logger = Logger.getLogger(RegistrationServiceImpl.class);

    private static final String USER_PERMISSIONS = "ROLE_USER";
    private static final String ADMIN_PERMISSIONS = "ROLE_ADMIN";
    //TODO: fix
    private static final String TEMPORARY_INVITATION_ID = "$2a$11$xHcnk0MN5oZ9ROJIUlWmW.HNyMj5pu.slIvs4oISWhvw7ijHP0nL2";

    private UserRepository repository;
    private BCryptPasswordEncoder encoder;

    @Override
    public RegistrationMsg createProfile(String name, String password, String invitationId) {
        try {
            if (isEmpty(invitationId) || encoder.matches(invitationId, TEMPORARY_INVITATION_ID)) {
                String role;
                if (isEmpty(invitationId)) {
                    role = USER_PERMISSIONS;
                } else {
                    role = ADMIN_PERMISSIONS;
                }
                repository.addUser(new UserEntity(name, encoder.encode(password), role));
                return SUCCESS;
            }
            return INVITATION_ID;
        } catch (PersistenceException e) {
            if (!(e.getCause() instanceof ConstraintViolationException)) {
                logger.error(e);
            }
            return EXIST;
        }
    }

    //
    // Setters for Dependency Injection.
    //

    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }
}
