package tk.jviewer.service;

import tk.jviewer.messages.RegistrationMsg;

/**
 * Registration service interface.
 * @author Evgeny Mironenko
 */
public interface RegistrationService {

    /**
     * Creates new profile in JViewer.
     * @param name username.
     * @param password password.
     * @param invitationId invitation id. It's not mandatory.
     * @param department department.
     * @return state about registration action.
     */
    RegistrationMsg createProfile(String name, String password, String invitationId, String department);
}
