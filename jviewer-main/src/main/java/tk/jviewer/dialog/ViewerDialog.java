package tk.jviewer.dialog;

import tk.jviewer.controller.LogoutController;
import tk.jviewer.model.Room;
import tk.jviewer.model.ViewerManagedBean;
import tk.jviewer.security.Permission;
import tk.jviewer.security.SecurityService;
import tk.jviewer.services.jc_v1_00.JcResult;
import tk.jviewer.wsc.jc.JcWsClient;

import java.io.Serializable;

/**
 * Viewer dialog.
 */
public class ViewerDialog implements Serializable {

    private static final long serialVersionUID = 5961684911167350079L;

    private String content;
    private String result;
    private boolean errorOccurred;

    private LogoutController logoutController;
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
        return SecurityService.userHasPermission(Permission.EDIT_VIEWER);
    }

    /**
     * Logging out from the application.
     * @return navigation rule.
     */
    public String logout() {
        return logoutController.logout();
    }

    /**
     * Sends entered content to JC Web Service and saves a result.
     */
    public void sendContent() {
        JcResult jcResult = jcWsClient.compileAndExecute(content);
        result = jcResult.getOutput();
        errorOccurred = jcResult.isErrorOccurred();
    }

    /**
     * Clears the current state of dialog.
     */
    public void clear() {
        result = null;
        errorOccurred = false;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public boolean isErrorOccurred() {
        return errorOccurred;
    }

    //
    // Dependency Injection
    //

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setJcWsClient(JcWsClient jcWsClient) {
        this.jcWsClient = jcWsClient;
    }

    public void setViewerManagedBean(ViewerManagedBean viewerManagedBean) {
        this.viewerManagedBean = viewerManagedBean;
    }
}
