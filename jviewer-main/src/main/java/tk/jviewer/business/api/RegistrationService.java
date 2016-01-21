package tk.jviewer.business.api;

import org.springframework.dao.DataIntegrityViolationException;

/**
 * Registration service interface.
 * @author Evgeny Mironenko
 */
public interface RegistrationService {

    /**
     * Creates a new profile in JViewer, based on specified name, password and invitation id.
     * Returns true if user was created. Returns false if invitation id is incorrect.
     * @param name username.
     * @param password password.
     * @param invitationId invitation id. It's not mandatory and could be null.
     * @return boolean result.
     * @throws DataIntegrityViolationException if user with specified name is already exist.
     */
    boolean createProfile(String name, String password, String invitationId) throws DataIntegrityViolationException;
}
