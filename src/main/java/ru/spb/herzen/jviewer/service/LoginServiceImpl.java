package ru.spb.herzen.jviewer.service;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.spb.herzen.jviewer.dao.LoginDao;
import ru.spb.herzen.jviewer.dao.ValidationDao;
import ru.spb.herzen.jviewer.model.RequestModel;
import ru.spb.herzen.jviewer.model.UserModel;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 5:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginServiceImpl implements LoginService {

    private ValidationDao validationDao;
    private LoginDao loginDao;

    @Override
    public UserModel getData(RequestModel userModel) {
        try{
            if(validationDao.getUserPassword(userModel.getName()).equals(userModel.getPassword())){
                return loginDao.getData(userModel.getName());
            }
        } catch(EmptyResultDataAccessException e){
            return null;
        }
        return null;
    }

    public void setValidationDao(ValidationDao validationDao) {
        this.validationDao = validationDao;
    }

    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }
}
