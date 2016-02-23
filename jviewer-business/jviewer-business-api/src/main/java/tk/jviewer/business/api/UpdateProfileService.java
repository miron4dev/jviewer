package tk.jviewer.business.api;

import javax.mail.MessagingException;

/**
 * Provides the methods to update a user profile.
 */
public interface UpdateProfileService {

    /**
     * Sends a request to get the admin permissions.
     *
     * @param username name of the user that requires the permissions.
     * @param email    email of the user that requires the permissions.
     */
    void sendAdminRequest(String username, String email) throws MessagingException;

    /**
     * Gives the admin permissions to user.
     *
     * @param encryptedData encrypted name of the user that will get the permissions.
     */
    void makeAdmin(String encryptedData);
}
