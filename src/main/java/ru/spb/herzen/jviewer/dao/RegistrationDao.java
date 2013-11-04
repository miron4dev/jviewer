package ru.spb.herzen.jviewer.dao;

import ru.spb.herzen.jviewer.model.UserModel;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RegistrationDao {

    void regProfile(UserModel userModel);
}
