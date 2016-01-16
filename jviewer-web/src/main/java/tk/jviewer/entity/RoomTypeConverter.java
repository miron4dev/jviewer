package tk.jviewer.entity;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Customer converter between {@link RoomEntity.Type} and PostgreSQL enum.
 * It should be removed after https://hibernate.atlassian.net/browse/HHH-9540 fix.
 */
public class RoomTypeConverter implements UserType {

    private static final int[] SQL_TYPES = new int[]{Types.OTHER};

    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Class returnedClass() {
        return Enum.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        Object pgObject = resultSet.getObject(names[0]);

        try {
            Method valueMethod = pgObject.getClass().getMethod("getValue");
            String value = (String) valueMethod.invoke(pgObject);
            return RoomEntity.Type.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        st.setObject(index, value, Types.OTHER);
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Enum) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}
