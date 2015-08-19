package tk.jviewer.dao;

import org.springframework.dao.DataAccessException;
import tk.jviewer.model.Room;

import java.util.List;

/**
 * Management Data Access Object interface.
 * @author Evgeny Mironenko
 */
public interface ManagementDao {

    /**
     * Returns the list of all available rooms in the database.
     * @return list of rooms.
     * @throws DataAccessException if sql query has been failed.
     */
    List<Room> getRooms() throws DataAccessException;

    /**
     * Adds a new room to the database.
     * @param name name of room.
     * @param password password of room.
     * @param type type of room.
     * @throws DataAccessException if sql query has been failed.
     */
    void createRoom(String name, String password, String type) throws DataAccessException;

    /**
     * Removes a chosen room from the database.
     * @param name name of the room.
     * @throws DataAccessException if sql query has been failed.
     */
    void deleteRoom(String name) throws DataAccessException;
}
