package tk.jviewer.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

/**
 * Contains state of the Viewer dialog.
 */
@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION)
public class ViewerManagedBean implements Serializable {

    private static final long serialVersionUID = 2855522805158876613L;

    private UIRoomAdaptor currentRoom;

    public UIRoomAdaptor getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(UIRoomAdaptor currentRoom) {
        this.currentRoom = currentRoom;
    }

}
