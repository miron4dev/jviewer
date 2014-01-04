package ru.spb.herzen.jviewer.model;

import javax.inject.Named;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class StudentModel implements UserModel, Serializable {

    private int id;
    private String name;
    private String password;
    private String role;
    private String temp;
    private String invitationID;
    private String faculty;
    private boolean enabled;

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
    public String getTemp() {
        return temp;
    }

    @Override
    public void setTemp(String temp) {
        this.temp = temp;
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
}
