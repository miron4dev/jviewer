package ru.spb.herzen.jviewer.dao;

/**
 * Management Data Access Object interface.
 * @author Evgeny Mironenko
 */
public interface ManagementDao {

    /**
     * Adds new room to database.
     * @param name name of room.
     * @param password password for room. Default is null.
     * @return success of creation.
     */
    boolean createRoom(String name, String password);

    /**
     * Removes chosen room from database.
     * @param name name of room.
     * @return success of removing.
     */
    boolean removeRoom(String name);
}
