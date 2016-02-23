package tk.jviewer.model;

import org.apache.commons.lang3.RandomStringUtils;
import tk.jviewer.business.model.RoomEntity;

/**
 * Extends {@link RoomEntity} by defining the additional fields which are required for UI.
 */
public class UIRoomAdaptor extends RoomEntity {

    private boolean privateRoom;

    public UIRoomAdaptor(Type type) {
        super(RandomStringUtils.random(15, true, true), type);
        privateRoom = true;
    }

    public UIRoomAdaptor(String name, Type type) {
        super(name, type);
    }

    public boolean isPrivateRoom() {
        return privateRoom;
    }

    public void setPrivateRoom(boolean privateRoom) {
        this.privateRoom = privateRoom;
    }
}
