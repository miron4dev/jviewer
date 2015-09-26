package tk.jviewer.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.jviewer.dao.LoginDao;
import tk.jviewer.dao.ValidationDao;
import tk.jviewer.profile.UserProfile;
import tk.jviewer.service.LoginService;

/**
 * Login service implementation.
 * @author Evgeny Mironenko
 */
public class LoginServiceImpl implements LoginService {

    private ValidationDao validationDao;
    private LoginDao loginDao;
    private BCryptPasswordEncoder encoder;

    /**
     * {@inheritDoc}
     * If authentication was failed, returns null.
     */
    @Override
    public UserProfile getData(String username, String password) {
        try{
            if(encoder.matches(password, validationDao.getUserPassword(username))){
                return loginDao.getData(username);
            }
            return null;
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    //
    // Setters for Dependency Injection.
    //

    public void setValidationDao(ValidationDao validationDao) {
        this.validationDao = validationDao;
    }

    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
}
