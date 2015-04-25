package tk.jviewer.info.service;

/**
 * Security manager.
 */
public interface SecurityService {

    /**
     * Returns true if user can be authenticated by specified username and password.
     * @param username name of user.
     * @param password pasword of user.
     * @return see description.
     */
    boolean authenticate(String username, String password);
}
