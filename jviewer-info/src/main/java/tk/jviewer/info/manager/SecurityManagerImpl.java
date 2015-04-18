package tk.jviewer.info.manager;

import org.springframework.dao.EmptyResultDataAccessException;
import tk.jviewer.info.dao.UserDao;

import java.lang.*;

/**
 * Security manager.
 */
public class SecurityManagerImpl implements SecurityManager {

    private UserDao dao;

    @Override
    public boolean authenticate(String username, String password) {
        try {
            return password.equals(dao.getUserByName(username).getPassword());
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    //
    // Dependency injection
    //

    public void setDao(UserDao dao) {
        this.dao = dao;
    }
}
