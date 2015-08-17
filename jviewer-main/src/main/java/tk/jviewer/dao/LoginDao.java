package tk.jviewer.dao;

import tk.jviewer.model.UserModel;

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
    UserModel getData(String name);
}
