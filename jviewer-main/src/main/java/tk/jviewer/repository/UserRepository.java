package tk.jviewer.repository;

import tk.jviewer.entity.UserEntity;

/**
 * Defines user repository interface.
 */
public interface UserRepository {

    /**
     * Adds new user.
     * @param user fulfilled instance of user.
     */
    void addUser(UserEntity user);

    /**
     * Returns user by specified username.
     * @param username username.
     * @return see description.
     */
    UserEntity getUser(String username);
}
