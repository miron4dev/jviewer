package tk.jviewer.dialog;

import tk.jviewer.controller.LogoutController;
import tk.jviewer.model.Room;
import tk.jviewer.model.ViewerManagedBean;
import tk.jviewer.security.Permission;
import tk.jviewer.security.SecurityService;

import java.io.Serializable;

/**
 * Viewer dialog backing bean.
 */
public class ViewerDialog implements Serializable {

    private static final long serialVersionUID = 5961684911167350079L;

    private LogoutController logoutController;
    private ViewerManagedBean viewerManagedBean;

    /**
     * Returns the name of the current room.
     *
     * @return see description.
     */
    public String getRoomName() {
        return viewerManagedBean.getCurrentRoom().getName();
    }

    /**
     * Returns the type of the current room.
     *
     * @return see description.
     */
    public Room.Type getRoomType() {
        return viewerManagedBean.getCurrentRoom().getType();
    }

    /**
     * Returns true if user has edit permissions.
     *
     * @return see description.
     */
    public boolean hasEditPermissions() {
        return SecurityService.userHasPermission(Permission.EDIT_VIEWER);
    }

    /**
     * Logs out from JViewer.
     *
     * @return see description.
     */
    public String logout() {
        return logoutController.logout();
    }

    //
    // Dependency Injection
    //

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setViewerManagedBean(ViewerManagedBean viewerManagedBean) {
        this.viewerManagedBean = viewerManagedBean;
    }
}
