package tk.jviewer.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Room representation.
 * @author Evgeny Mironenko
 */
public class Room implements Serializable {

    private static final long serialVersionUID = -8138653647619806300L;

    public enum Type {
        HTML,
        JAVA
    }

    private Integer id;
    private String name;
    private String password;
    private Type type;

    public Room() {
    }

    public Room(String name, String password, Type type) {
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        return new EqualsBuilder()
                .append(id, room.id)
                .append(name, room.name)
                .append(password, room.password)
                .append(type, room.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(password)
                .append(type)
                .toHashCode();
    }

}
