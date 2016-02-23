package tk.jviewer.dialog;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import tk.jviewer.model.UIRoomAdaptor;
import tk.jviewer.model.ViewerManagedBean;
import tk.jviewer.security.SecurityService;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static tk.jviewer.model.JViewerUriPath.*;
import static tk.jviewer.model.JViewerUriPath.NEWS_PAGE;

/**
 * Main dialog backing bean.
 */
public class MainDialog implements Serializable {

    private static final long serialVersionUID = 8687311151966287392L;

    private ViewerManagedBean viewerManagedBean;

    /**
     * @see SecurityService#isAuthenticated()
     */
    public boolean isAuthenticated() {
        return SecurityService.isAuthenticated();
    }

    /**
     * @see SecurityService#isAdmin()
     */
    public boolean isAdmin() {
        return SecurityService.isAdmin();
    }

    /**
     * @see SecurityService#getUsername()
     */
    public String getUsername() {
        return SecurityService.getUsername();
    }

    /**
     * @return JSF page for the navigation.
     * @see SecurityService#logout()
     */
    public String logout() {
        SecurityService.logout();
        return NEWS_PAGE.getJsfUri();
    }

    /**
     * Navigates to the private WEB room.
     */
    public void gotoWebRoom() throws IOException {
        gotoPrivateRoom(UIRoomAdaptor.Type.HTML);
    }

    /**
     * Navigates to the private Java room.
     */
    public void gotoJavaRoom() throws IOException {
        gotoPrivateRoom(UIRoomAdaptor.Type.JAVA);
    }

    /**
     * Opens Room List dialog.
     */
    public void openRoomList() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("roomList", options, null);
    }

    /**
     * Opens Edit Profile dialog.
     */
    public void editProfile() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("editProfile", options, null);
    }

    /**
     * On room chosen listener.
     *
     * @param event select event.
     * @throws IOException if the redirect to viewer page was failed.
     */
    public void onRoomChosen(SelectEvent event) throws IOException {
        UIRoomAdaptor room = (UIRoomAdaptor) event.getObject();
        viewerManagedBean.setCurrentRoom(room);
        FacesContext.getCurrentInstance().getExternalContext().redirect(VIEWER_PAGE.getUri());
    }

    /**
     * Navigates to the private room.
     *
     * @param type type of the room.
     * @throws IOException if the redirect to viewer page was failed.
     */
    private void gotoPrivateRoom(UIRoomAdaptor.Type type) throws IOException {
        UIRoomAdaptor room = new UIRoomAdaptor(type);
        room.setPrivateRoom(true);
        viewerManagedBean.setCurrentRoom(room);
        FacesContext.getCurrentInstance().getExternalContext().redirect(VIEWER_PAGE.getUri());
    }

    //
    // Dependency Injection
    //

    public void setViewerManagedBean(ViewerManagedBean viewerManagedBean) {
        this.viewerManagedBean = viewerManagedBean;
    }
}
