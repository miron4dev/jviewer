package tk.jviewer.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import tk.jviewer.business.model.RoomEntity;

import java.io.Serializable;

/**
 * Contains state of the Viewer dialog.
 */
@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION)
public class ViewerManagedBean implements Serializable {

    private static final long serialVersionUID = 2855522805158876613L;

    private RoomEntity currentRoom;

    public RoomEntity getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(RoomEntity currentRoom) {
        this.currentRoom = currentRoom;
    }

}
