package tk.jviewer.model;

import java.io.Serializable;

/**
 * Request model implementation. Used on the JSF pages for sending requests.
 * @author Evgeny Mironenko
 */
public class RequestModel implements Serializable {

    private static final long serialVersionUID = 5648043578683281691L;

    private String name;
    private String password;
    private String temp;
    private String invitationID;
    private String faculty;
    private String role;

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

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
