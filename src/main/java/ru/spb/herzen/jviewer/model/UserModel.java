package ru.spb.herzen.jviewer.model;

import java.io.Serializable;

/**
 * User model interface.
 * @author Evgeny Mironenko
 */
public interface UserModel extends Serializable {

    /**
     * Gets user id in database.
     * @return user id.
     */
    int getId();

    /**
     * Sets user id in database.
     * @param id id of user.
     */
    void setId(int id);

    /**
     * Gets user name.
     * @return name
     */
    String getName();

    /**
     * Sets user name.
     * @param name of user.
     */
    void setName(String name);

    /**
     * Gets user password.
     * @return password
     */
    String getPassword();

    /**
     * Sets user password.
     * @param password user password
     */
    void setPassword(String password);

    /**
     * Gets user role in system.
     * @return role
     */
    String getRole();

    /**
     * Sets user role in system.
     * @param role of user
     */
    void setRole(String role);

    /**
     * Gets user invitation Id.
     * @return invitation Id
     */
    String getInvitationID();

    /**
     * Sets user invitation Id.
     * @param invitationID id
     */
    void setInvitationID(String invitationID);

    /**
     * Gets user faculty.
     * @return faculty
     */
    String getFaculty();

    /**
     * Sets faculty of user.
     * @param faculty of user
     */
    void setFaculty(String faculty);

    /**
     * Gets availability of user.
     * @return user availability.
     */
    boolean isEnabled();

    /**
     * Sets availability of user.
     * @param enabled availability value
     */
    void setEnabled(boolean enabled);

    /**
     * Gets current chosen room.
     * @return chosen room.
     */
    String getCurrentRoom();

    /**
     * Sets current room.
     * @param currentRoom name of chosen room
     */
    void setCurrentRoom(String currentRoom);
}
