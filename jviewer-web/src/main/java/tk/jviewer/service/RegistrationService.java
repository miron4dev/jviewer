package tk.jviewer.service;

import org.springframework.dao.DataIntegrityViolationException;

/**
 * Registration service interface.
 * @author Evgeny Mironenko
 */
public interface RegistrationService {

    /**
     * Creates a new profile in JViewer, based on specified name, email and password.
     * @param name username.
     * @param email email.
     * @param password password.
     * @throws DataIntegrityViolationException if user with specified name is already exist.
     */
    void createProfile(String name, String email, String password) throws DataIntegrityViolationException;
}
