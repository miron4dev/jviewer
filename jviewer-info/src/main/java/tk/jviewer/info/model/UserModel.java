package tk.jviewer.info.model;

import java.io.Serializable;

/**
 * Model of user.
 */
public class UserModel implements Serializable {

    private String name;
    private String password;

    private boolean authenticated;

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

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
