package tk.jviewer.info.service;

import org.springframework.dao.EmptyResultDataAccessException;
import tk.jviewer.info.dao.UserDao;

import java.lang.*;

/**
 * Security manager.
 */
public class SecurityServiceImpl implements SecurityService {

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
