package tk.jviewer.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import tk.jviewer.dao.LoginDao;
import tk.jviewer.dao.ValidationDao;
import tk.jviewer.model.RequestModel;
import tk.jviewer.model.UserModel;
import tk.jviewer.service.LoginService;

/**
 * Login service implementation.
 * @author Evgeny Mironenko
 */
public class LoginServiceImpl implements LoginService {

    private ValidationDao validationDao;
    private LoginDao loginDao;

    /**
     * @see tk.jviewer.service.LoginService#getData(RequestModel)
     * If validation will be failed, return null.
     */
    @Override
    public UserModel getData(RequestModel userModel) {
        try{
            if(validationDao.getUserPassword(userModel.getName()).equals(userModel.getPassword())){
                return loginDao.getData(userModel.getName());
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
}
