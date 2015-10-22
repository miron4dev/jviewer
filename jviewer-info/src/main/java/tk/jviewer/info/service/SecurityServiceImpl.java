package tk.jviewer.info.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.jviewer.info.dao.UserDao;

import java.lang.*;

/**
 * Security manager.
 */
public class SecurityServiceImpl implements SecurityService {

    private UserDao dao;
    private BCryptPasswordEncoder encoder;

    @Override
    public boolean authenticate(String username, String password) {
        try {
            return encoder.matches(password, dao.getUserByName(username).getPassword());
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

    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
}
