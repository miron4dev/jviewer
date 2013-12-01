package ru.spb.herzen.jviewer.service;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.spb.herzen.jviewer.dao.RegistrationDao;
import ru.spb.herzen.jviewer.dao.ValidationDao;
import ru.spb.herzen.jviewer.messages.RegistrationMsg;
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
    public RegistrationMsg regProfile(UserModel userModel) {
        try{
            validationDao.checkUser(userModel.getName());
            return RegistrationMsg.EXIST;
        } catch (EmptyResultDataAccessException e){
            return checkUserData(userModel);
        }
    }

    private RegistrationMsg checkUserData(UserModel userModel){
        if(userModel.getInvitationID().isEmpty() || registrationDao.getInvitationID().equals(userModel.getInvitationID())){
            if(userModel.getInvitationID().isEmpty()){
                userModel.setRole("user");
            } else{
                userModel.setRole("admin");
            }
            registrationDao.regProfile(userModel);
            return RegistrationMsg.SUCCESS;
        }
        return RegistrationMsg.INVITATION_ID;
    }

    public void setValidationDao(ValidationDao validationDao) {
        this.validationDao = validationDao;
    }

    public void setRegistrationDao(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }
}
