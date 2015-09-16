package tk.jviewer.model;

/**
 * Contains state of the Viewer dialog.
 */
public class ViewerManagedBean {

    private Room currentRoom;

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
