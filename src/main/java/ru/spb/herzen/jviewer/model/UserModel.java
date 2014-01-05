package ru.spb.herzen.jviewer.model;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
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
}
