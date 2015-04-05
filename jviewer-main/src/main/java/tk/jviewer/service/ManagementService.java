package tk.jviewer.service;

/**
 * Management service interface.
 * @author Evgeny Mironenko
 */
public interface ManagementService {

    /**
     * Creates new room.
     * @param room name of room.
     * @param password password for room. Default is null.
     * @return result of creation.
     */
    boolean createRoom(String room, String password);

    /**
     * Removes chosen room.
     * @param room name of room.
     * @return result of removing.
     */
    boolean removeRoom(String room);
}
