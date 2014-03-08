package ru.spb.herzen.jviewer.dao;

import org.springframework.dao.DataAccessException;

/**
 * Registration Data Access Object interface.
 * @author Evgeny Mironenko
 */
public interface RegistrationDao {

    /**
     * Adds new user to database.
     * @param name name of profile.
     * @param password password of profile.
     * @param role role of profile. Default is USER_ROLE.
     * @param faculty faculty of profile.
     * @throws DataAccessException if user with this name is already exist.
     */
    void regProfile(String name, String password, String role, String faculty) throws DataAccessException;

    /**
     * Gets invitation ID for professor registration from database.
     * @return invitation ID.
     */
    String getInvitationID();
}
