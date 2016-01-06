package tk.jviewer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A localization entity.
 */
@Entity
@Table(name = "localization", schema = "jviewer_main", catalog = "jviewer")
public class LocalizationEntity {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "key", unique = true, nullable = false)
    private String key;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "locale", nullable = false)
    private String locale;

    @Column(name = "dialog_name", nullable = false)
    private String dialogName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getDialogName() {
        return dialogName;
    }

    public void setDialogName(String dialogName) {
        this.dialogName = dialogName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalizationEntity that = (LocalizationEntity) o;

        if (id != that.id) return false;
        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (locale != null ? !locale.equals(that.locale) : that.locale != null) return false;
        if (dialogName != null ? !dialogName.equals(that.dialogName) : that.dialogName != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (locale != null ? locale.hashCode() : 0);
        result = 31 * result + (dialogName != null ? dialogName.hashCode() : 0);
        return result;
    }
}
