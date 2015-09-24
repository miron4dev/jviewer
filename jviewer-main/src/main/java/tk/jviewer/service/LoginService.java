package tk.jviewer.service;

import tk.jviewer.profile.UserProfile;

/**
 * Login service interface.
 * @author Evgeny Mironenko
 */
public interface LoginService {

    /**
     * Returns the user profile by specified username and password.
     * @param username name of user.
     * @param password password of user.
     * @return see description.
     */
    UserProfile getData(String username, String password);
}
