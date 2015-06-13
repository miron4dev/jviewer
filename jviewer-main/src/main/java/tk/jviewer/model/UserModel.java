package tk.jviewer.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * User model implementation.
 * @author Evgeny Mironenko
 */
public class UserModel {

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
        if(obj instanceof UserModel) {
            UserModel user = (UserModel)obj;
            return id == user.id && enabled == user.isEnabled() && StringUtils.equals(name, user.getName()) &&
                    StringUtils.equals(password, user.getPassword()) && StringUtils.equals(role, user.getRole()) &&
                    StringUtils.equals(invitationID, user.getInvitationID()) && StringUtils.equals(faculty, user.getFaculty()) &&
                    StringUtils.equals(currentRoom, user.getCurrentRoom());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).append(password).append(role).append(invitationID).append(faculty)
                .append(currentRoom).build();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getInvitationID() {
        return invitationID;
    }

    public void setInvitationID(String invitationID) {
        this.invitationID = invitationID;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

}
