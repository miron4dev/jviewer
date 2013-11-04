package ru.spb.herzen.jviewer.service;

import ru.spb.herzen.jviewer.dao.RegistrationDao;
import ru.spb.herzen.jviewer.dao.ValidationDao;
import ru.spb.herzen.jviewer.model.UserModel;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 3:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationServiceImpl implements RegistrationService, Serializable {

    private ValidationDao validationDao;
    private RegistrationDao registrationDao;

    @Override
    public boolean regProfile(UserModel userModel) {
        if(validationDao.checkUser(userModel.getName())){
            registrationDao.regProfile(userModel);
            return true;
        }
        else {
            return false;
        }
    }

    public void setValidationDao(ValidationDao validationDao) {
        this.validationDao = validationDao;
    }

    public void setRegistrationDao(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }
}
