package tk.jviewer.profile;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User profile implementation.
 * @author Evgeny Mironenko
 */
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1760830794494874370L;

    private int id;
    private String name;
    private String password;
    private String role;
    private String invitationID;
    private String faculty;
    private boolean enabled;
    private List<Permission> permissions = new ArrayList<>();

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof UserProfile) {
            UserProfile user = (UserProfile)obj;
            return id == user.id && enabled == user.isEnabled() && StringUtils.equals(name, user.getName()) &&
                    StringUtils.equals(password, user.getPassword()) && StringUtils.equals(role, user.getRole()) &&
                    StringUtils.equals(invitationID, user.getInvitationID()) && StringUtils.equals(faculty, user.getFaculty());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).append(password).append(role).append(invitationID).append(faculty).build();
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

    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
