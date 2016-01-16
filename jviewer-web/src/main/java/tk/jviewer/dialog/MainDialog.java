package tk.jviewer.dialog;

import tk.jviewer.security.SecurityService;

import java.io.Serializable;

/**
 * Main dialog backing bean.
 */
public class MainDialog implements Serializable {

    private static final long serialVersionUID = 8687311151966287392L;

    /**
     * @see SecurityService#isAuthenticated()
     */
    public boolean isAuthenticated() {
        return SecurityService.isAuthenticated();
    }

    /**
     * @see SecurityService#getUsername()
     */
    public String getUsername() {
        return SecurityService.getUsername();
    }

    /**
     * @see SecurityService#logout()
     */
    public String logout() {
        return SecurityService.logout();
    }
}
