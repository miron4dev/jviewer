package tk.jviewer.security;

import java.io.Serializable;
import java.util.List;

/**
 * User profile implementation.
 * @author Evgeny Mironenko
 */
class UserProfile implements Serializable {

    private static final long serialVersionUID = -3700564961683923927L;

    private String name;
    private String email;
    private List<Permission> permissions;

    public UserProfile(String name, String email, List<Permission> permissions) {
        this.name = name;
        this.email = email;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }
}
