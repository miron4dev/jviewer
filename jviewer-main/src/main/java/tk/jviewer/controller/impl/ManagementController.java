package tk.jviewer.controller.impl;

import org.springframework.dao.DataAccessException;
import tk.jviewer.dao.ManagementDao;
import tk.jviewer.model.Room;

import java.util.List;

/**
 * Management controller.
 * @author Evgeny Mironenko
 */
public class ManagementController {

    private ManagementDao managementDao;

    /**
     * Returns the list of all available rooms.
     * @return list of rooms.
     */
    public List<Room> getRooms() throws DataAccessException {
        return managementDao.getRooms();
    }

    /**
     * Creates a new room.
     * @param room the instance of {@link Room}.
     */
    public void createRoom(Room room) throws DataAccessException {
        managementDao.createRoom(room.getName(), room.getPassword());
    }

    /**
     * Deletes a chosen room.
     * @param room the instance of {@link Room}.
     */
    public void deleteRoom(Room room) throws DataAccessException {
        managementDao.deleteRoom(room.getName());
    }

    //
    // Dependency Injection.
    //

    public void setManagementDao(ManagementDao managementDao) {
        this.managementDao = managementDao;
    }
}
