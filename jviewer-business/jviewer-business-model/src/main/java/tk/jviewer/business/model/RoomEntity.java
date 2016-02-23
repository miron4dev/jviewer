package tk.jviewer.business.model;

import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A room entity.
 */
@Entity
@Table(name = "room", schema = "jviewer_main", catalog = "jviewer")
@TypeDef(name = "roomTypeConverter", typeClass = RoomTypeConverter.class)
public class RoomEntity {

    public enum Type {
        HTML,
        JAVA
    }

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "type", nullable = false)
    @org.hibernate.annotations.Type(type = "roomTypeConverter")
    private Type type;

    public RoomEntity() {
    }

    public RoomEntity(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        RoomEntity that = (RoomEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
