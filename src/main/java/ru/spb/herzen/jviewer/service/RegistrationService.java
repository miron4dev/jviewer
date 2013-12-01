package ru.spb.herzen.jviewer.service;

import ru.spb.herzen.jviewer.messages.RegistrationMsg;
import ru.spb.herzen.jviewer.model.UserModel;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RegistrationService {

    RegistrationMsg regProfile(UserModel userModel);
}
