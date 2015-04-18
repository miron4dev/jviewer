package tk.jviewer.info.manager;

/**
 * Security manager.
 */
public interface SecurityManager {

    /**
     * Returns true if user can be authenticated by specified username and password.
     * @param username name of user.
     * @param password pasword of user.
     * @return see description.
     */
    boolean authenticate(String username, String password);
}
