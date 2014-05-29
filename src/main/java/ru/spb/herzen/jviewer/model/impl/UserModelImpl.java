package ru.spb.herzen.jviewer.model.impl;

import ru.spb.herzen.jviewer.model.UserModel;

import static ru.spb.herzen.jviewer.model.impl.CommonModel.*;

import java.io.Serializable;

/**
 * User model implementation.
 * @author Evgeny Mironenko
 */
public class UserModelImpl implements UserModel {

    private int id;
    private String name;
    private String password;
    private String role;
    private String invitationID;
    private String faculty;
    private boolean enabled;
    private String currentRoom;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof UserModelImpl) {
            UserModelImpl user = (UserModelImpl)obj;
            return id == user.id && enabled == user.isEnabled() && equalsString(name, user.getName()) &&
                    equalsString(password, user.getPassword()) && equalsString(role, user.getRole()) &&
                    equalsString(invitationID, user.getInvitationID()) && equalsString(faculty, user.getFaculty()) &&
                    equalsString(currentRoom, user.getCurrentRoom());
        }
        return false;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getInvitationID() {
        return invitationID;
    }

    @Override
    public void setInvitationID(String invitationID) {
        this.invitationID = invitationID;
    }

    @Override
    public String getFaculty() {
        return faculty;
    }

    @Override
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getCurrentRoom() {
        return currentRoom;
    }

    @Override
    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

}
