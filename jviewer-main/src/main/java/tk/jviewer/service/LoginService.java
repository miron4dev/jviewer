package tk.jviewer.service;

import tk.jviewer.model.RequestModel;
import tk.jviewer.model.UserModel;

/**
 * Login service interface.
 * @author Evgeny Mironenko
 */
public interface LoginService {

    /**
     * Gets user data from request model.
     * @param requestModel current request model
     * @return user data object.
     */
    UserModel getData(RequestModel requestModel);
}
