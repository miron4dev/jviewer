package tk.jviewer.business.api;

import org.springframework.dao.DataIntegrityViolationException;

import javax.mail.MessagingException;

/**
 * Registration service interface.
 *
 * @author Evgeny Mironenko
 */
public interface RegistrationService {

    /**
     * Creates a new profile in JViewer, based on specified encrypted data.
     *
     * @param encryptedData encrypted name, password and email.
     * @throws DataIntegrityViolationException if user with the specified name or email is already exist.
     */
    void createProfile(String encryptedData) throws DataIntegrityViolationException;

    /**
     * Sends a registration link to the specified email.
     *
     * @param name     username.
     * @param password password.
     * @param email    email.
     * @throws MessagingException if the sending was failed.
     */
    void sendEmailConfirmation(String name, String password, String email) throws DataIntegrityViolationException, MessagingException;
}
