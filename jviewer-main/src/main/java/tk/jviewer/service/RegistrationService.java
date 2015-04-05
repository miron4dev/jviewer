package tk.jviewer.service;

import tk.jviewer.messages.RegistrationMsg;
import tk.jviewer.model.impl.RequestModel;

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
