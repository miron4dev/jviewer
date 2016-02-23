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
     * @throws MessagingException if could not send the email.
     */
    void sendAdminRequest(String username, String email) throws MessagingException;

    /**
     * Gives the admin permissions to user.
     *
     * @param encryptedData encrypted name of the user that will get the permissions.
     */
    void makeAdmin(String encryptedData);

    /**
     * Sends a request to change the password.
     *
     * @param username    name of the user that requires to change password.
     * @param email       email of the user that requires to change password.
     * @param newPassword new password.
     * @throws MessagingException if could not send the email.
     */
    void sendChangePasswordRequest(String username, String email, String newPassword) throws MessagingException;

    /**
     * Change the user password.
     *
     * @param encryptedData encrypted name of the user and new password.
     * @return new password.
     */
    String changePassword(String encryptedData);

    /**
     * Sends a request to reset the user password.
     *
     * @param email email of the user that requires to reset password.
     * @throws MessagingException if could not send the email.
     */
    void sendResetPasswordRequest(String email) throws MessagingException;
}
