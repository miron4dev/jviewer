package ru.spb.herzen.jviewer.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.spb.herzen.jviewer.dao.RegistrationDao;
import ru.spb.herzen.jviewer.dao.ValidationDao;
import ru.spb.herzen.jviewer.messages.RegistrationMsg;
import ru.spb.herzen.jviewer.model.impl.RequestModel;
import ru.spb.herzen.jviewer.service.RegistrationService;

import java.io.Serializable;

/**
 * Registration service implementation.
 * @author Evgeny Mironenko
 */
public class RegistrationServiceImpl implements RegistrationService, Serializable {

    private ValidationDao validationDao;
    private RegistrationDao registrationDao;

    /**
     * @see ru.spb.herzen.jviewer.service.RegistrationService#regProfile(ru.spb.herzen.jviewer.model.impl.RequestModel)
     */
    @Override
    public RegistrationMsg regProfile(RequestModel requestModel) {
        try{
            validationDao.checkUser(requestModel.getName());
            return RegistrationMsg.EXIST;
        } catch (EmptyResultDataAccessException e){
            return checkUserData(requestModel);
        }
    }

    /**
     * Checks user data and creates new profile, or returns error message.
     * @param requestModel request model of current user
     * @return message about success of registration
     */
    private RegistrationMsg checkUserData(RequestModel requestModel) {
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

    //
    // Setters for Dependency Injection.
    //

    public void setValidationDao(ValidationDao validationDao) {
        this.validationDao = validationDao;
    }

    public void setRegistrationDao(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }
}
