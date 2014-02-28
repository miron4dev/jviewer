package ru.spb.herzen.jviewer.service;

import ru.spb.herzen.jviewer.messages.RegistrationMsg;
import ru.spb.herzen.jviewer.model.RequestModel;
import ru.spb.herzen.jviewer.model.UserModel;

/**
 * Registration service interface.
 * @author Evgeny Mironenko
 */
public interface RegistrationService {

    /**
     * Creates new profile from request model.
     * @param userModel current request model
     * @return message about result of registration
     */
    RegistrationMsg regProfile(RequestModel userModel);
}
