package tk.jviewer.dialog;

import tk.jviewer.controller.LoginController;
import tk.jviewer.model.Room;
import tk.jviewer.model.ViewerManagedBean;
import tk.jviewer.profile.Permission;
import tk.jviewer.profile.UserProfile;
import tk.jviewer.wsc.jc.JcWsClient;

import java.io.Serializable;

/**
 * Viewer dialog.
 */
public class ViewerDialog implements Serializable {

    private static final long serialVersionUID = 5961684911167350079L;

    private String content;
    private String result;

    private UserProfile userProfile;
    private LoginController loginController;
    private JcWsClient jcWsClient;
    private ViewerManagedBean viewerManagedBean;

    /**
     * Returns the current room.
     * @return see description.
     */
    public String getCurrentRoom() {
        return viewerManagedBean.getCurrentRoom().getName();
    }

    public boolean isJavaRoom() {
        return viewerManagedBean.getCurrentRoom().getType() == Room.Type.JAVA;
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

    /**
     * Sends entered content to JC Web Service and saves a result.
     */
    public void sendContent() {
        result = jcWsClient.compileAndExecute(content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
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

    public void setJcWsClient(JcWsClient jcWsClient) {
        this.jcWsClient = jcWsClient;
    }

    public void setViewerManagedBean(ViewerManagedBean viewerManagedBean) {
        this.viewerManagedBean = viewerManagedBean;
    }
}
