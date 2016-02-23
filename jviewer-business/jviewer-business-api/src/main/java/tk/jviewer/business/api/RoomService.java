package tk.jviewer.business.api;

import tk.jviewer.business.model.RoomEntity;

import java.util.List;

/**
 * Provides the methods for retrieving, updating, deleting of room entity.
 *
 * @author Evgeny Mironenko
 */
public interface RoomService {

    /**
     * Returns a list of all available rooms.
     *
     * @return list of rooms.
     */
    List<RoomEntity> getRooms();

    /**
     * Deletes specified room.
     *
     * @param room room to be deleted.
     */
    void deleteRoom(RoomEntity room);

    /**
     * Creates the new room.
     *
     * @param name name of the new room.
     * @param type types of the new room.
     */
    void createRoom(String name, RoomEntity.Type type);
}
