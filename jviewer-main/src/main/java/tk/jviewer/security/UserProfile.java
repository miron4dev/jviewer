package tk.jviewer.security;

import java.util.List;

/**
 * User profile implementation.
 * @author Evgeny Mironenko
 */
class UserProfile {

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
