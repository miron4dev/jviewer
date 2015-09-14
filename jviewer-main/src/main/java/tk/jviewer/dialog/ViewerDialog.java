package tk.jviewer.dialog;

import tk.jviewer.controller.LoginController;
import tk.jviewer.profile.Permission;
import tk.jviewer.profile.UserProfile;

import java.io.Serializable;

/**
 * Viewer dialog.
 */
public class ViewerDialog implements Serializable {

    private static final long serialVersionUID = 5961684911167350079L;

    private UserProfile userProfile;
    private LoginController loginController;

    /**
     * Returns the current room.
     * @return see description.
     */
    public String getCurrentRoom() {
        return userProfile.getCurrentRoom();
    }

    /**
     * Returns true if user has the permissions for edit viewer.
     * @return see description.
     */
    public boolean hasEditPermissions() {
        return userProfile.hasPermission(Permission.EDIT_VIEWER);
    }

    /**
     * Logging out from the application.
     * @return navigation rule.
     */
    public String logout() {
        return loginController.logout();
    }

    public void sendContent(String content) {
        //TODO
    }

    //
    // Dependency Injection
    //

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
