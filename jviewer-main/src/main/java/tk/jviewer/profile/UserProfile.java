package tk.jviewer.profile;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import tk.jviewer.model.Quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User profile implementation.
 * @author Evgeny Mironenko
 */
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1760830794494874370L;

    private String name;
    private String role;
    private List<Permission> permissions = new ArrayList<>();

    private List<Quiz> quizzes = new ArrayList<>();

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

}
