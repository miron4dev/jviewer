package ru.spb.herzen.jviewer.service;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.spb.herzen.jviewer.dao.RegistrationDao;
import ru.spb.herzen.jviewer.dao.ValidationDao;
import ru.spb.herzen.jviewer.messages.RegistrationMsg;
import ru.spb.herzen.jviewer.model.RequestModel;
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
    public RegistrationMsg regProfile(RequestModel requestModel) {
        try{
            validationDao.checkUser(requestModel.getName());
            return RegistrationMsg.EXIST;
        } catch (EmptyResultDataAccessException e){
            return checkUserData(requestModel);
        }
    }

    private RegistrationMsg checkUserData(RequestModel requestModel){
        if(requestModel.getInvitationID().isEmpty() || registrationDao.getInvitationID().equals(requestModel.getInvitationID())){
            if(requestModel.getInvitationID().isEmpty()){
                requestModel.setRole("ROLE_USER");
            } else{
                requestModel.setRole("ROLE_ADMIN");
            }
            registrationDao.regProfile(requestModel.getName(), requestModel.getPassword(), requestModel.getRole(), requestModel.getFaculty());
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
