package tk.jviewer.business.api;

import tk.jviewer.business.model.RoomEntity;

import java.util.List;

/**
 * Management service interface. Provides method for rooms and quizes management.
 * @author Evgeny Mironenko
 */
public interface ManagementService {

    /**
     * Returns a list of all available rooms in the database.
     * @return list of rooms.
     */
    List<RoomEntity> getRooms();

    /**
     * Adds a new room to the database.
     * @param name name of room.
     * @param type type of room.
     * @return created room.
     */
    RoomEntity createRoom(String name, RoomEntity.Type type);

    /**
     * Removes a chosen room from the database.
     * @param roomEntity room entity.
     */
    void deleteRoom(RoomEntity roomEntity);
}
