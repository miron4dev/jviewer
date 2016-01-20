package tk.jviewer.service;

import tk.jviewer.entity.RoomEntity;

import java.util.List;

/**
 * Provides the methods for retrieving, updating, deleting of room entity.
 *
 * @author Evgeny Mironenko
 */
public interface RoomService {

    /**
     * Returns a list of all available rooms.
     * @return list of rooms.
     */
    List<RoomEntity> getRooms();
}
