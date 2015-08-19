package tk.jviewer.service;

import tk.jviewer.model.RequestModel;
import tk.jviewer.profile.UserProfile;

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
    UserProfile getData(RequestModel requestModel);
}
