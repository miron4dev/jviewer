package tk.jviewer.dialog;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import tk.jviewer.model.UIRoomAdaptor;
import tk.jviewer.model.ViewerManagedBean;
import tk.jviewer.security.SecurityService;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * Viewer dialog backing bean.
 */
public class ViewerDialog implements Serializable {

    private static final long serialVersionUID = 5961684911167350079L;

    private ViewerManagedBean viewerManagedBean;

    private String result;

    /**
     * Returns the name of the current room.
     *
     * @return see description.
     */
    public String getRoomName() {
        return getCurrentRoom().getName();
    }

    /**
     * Returns the type of the current room.
     *
     * @return see description.
     */
    public UIRoomAdaptor.Type getRoomType() {
        return getCurrentRoom().getType();
    }

    /**
     * Returns true if user has edit permissions.
     *
     * @return see description.
     */
    public boolean hasEditPermissions() {
        return SecurityService.isAdmin() || getCurrentRoom().isPrivateRoom();
    }

    /**
     * Returns the downloadable result in html format.
     *
     * @return see description.
     */
    public StreamedContent getFile() {
        return new DefaultStreamedContent(new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8)), "text/html", "result.html");
    }

    /**
     * Returns the result of code execution. It is used only for export and can contain not the last value!
     *
     * @return see description.
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets the result of code execution.
     *
     * @param result escaped html, which is ready to be downloaded.
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Returns the current room.
     *
     * @return see description.
     */
    private UIRoomAdaptor getCurrentRoom() {
        return viewerManagedBean.getCurrentRoom();
    }

    //
    // Dependency Injection
    //

    public void setViewerManagedBean(ViewerManagedBean viewerManagedBean) {
        this.viewerManagedBean = viewerManagedBean;
    }
}
