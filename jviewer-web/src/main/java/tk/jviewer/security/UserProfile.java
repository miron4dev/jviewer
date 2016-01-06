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
    private List<Permission> permissions;

    public UserProfile(String name, List<Permission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }
}
