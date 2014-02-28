package ru.spb.herzen.jviewer.model;

/**
 * User model interface.
 * @author Evgeny Mironenko
 */
public interface UserModel {

    int getId();
    void setId(int id);
    String getName();
    void setName(String name);
    String getPassword();
    void setPassword(String password);
    String getRole();
    void setRole(String role);
    String getInvitationID();
    void setInvitationID(String invitationID);
    String getFaculty();
    void setFaculty(String faculty);
    boolean isEnabled();
    void setEnabled(boolean enabled);
    String getCurrentRoom();
    void setCurrentRoom(String currentRoom);
}
