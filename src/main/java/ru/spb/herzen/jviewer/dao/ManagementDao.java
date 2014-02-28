package ru.spb.herzen.jviewer.dao;

import org.springframework.dao.DataAccessException;

/**
 * Management Data Access Object interface.
 * @author Evgeny Mironenko
 */
public interface ManagementDao {

    /**
     * Adds new room to database.
     * @param name name of room.
     * @param password password for room. Default is null.
     * @throws DataAccessException if room is already exist.
     */
    void createRoom(String name, String password) throws DataAccessException;

    /**
     * Removes chosen room from database.
     * @param name name of room.
     * @throws DataAccessException if room is not exist.
     */
    void removeRoom(String name) throws DataAccessException;
}
