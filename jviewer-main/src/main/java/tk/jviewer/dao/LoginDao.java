package tk.jviewer.dao;

import tk.jviewer.profile.UserProfile;

/**
 * Login Data Access Object interface.
 * @author Evgeny Mironenko
 */
public interface LoginDao {

    /**
     * Gets user data from database.
     * @param name name of user.
     * @return user data object.
     */
    UserProfile getData(String name);
}
