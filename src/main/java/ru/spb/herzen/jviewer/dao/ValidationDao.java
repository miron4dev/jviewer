package ru.spb.herzen.jviewer.dao;

import org.springframework.dao.EmptyResultDataAccessException;

/**
 * Validation Data Access Object interface.
 * @author Evgeny Mironenko
 */
public interface ValidationDao {

    /**
     * Checks, that user is available in database.
     * @param name name of user.
     * @throws EmptyResultDataAccessException if user is not exist.
     */
    void checkUser(String name) throws EmptyResultDataAccessException;

    /**
     * Gets user password from database.
     * @param name name of user.
     * @return password of user.
     * @throws EmptyResultDataAccessException if user is not exist.
     */
    String getUserPassword(String name) throws EmptyResultDataAccessException;
}
