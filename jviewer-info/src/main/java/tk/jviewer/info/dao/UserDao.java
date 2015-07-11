package tk.jviewer.info.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import tk.jviewer.info.model.UserProfile;

/**
 * Dao layer for user management.
 */
public interface UserDao {

    /**
     * Returns the user model by name.
     * @param name name of the users.
     * @return see description.
     * @throws EmptyResultDataAccessException if user with this name does not exists.
     */
    UserProfile getUserByName(String name) throws EmptyResultDataAccessException;
}
