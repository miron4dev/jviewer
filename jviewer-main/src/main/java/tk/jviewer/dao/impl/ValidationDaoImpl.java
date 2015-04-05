package tk.jviewer.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.ValidationDao;

/**
 * Validation Data Access Object implementation.
 * @author Evgeny Mironenko
 */
public class ValidationDaoImpl extends JdbcDaoSupport implements ValidationDao {

    /**
     * @see  tk.jviewer.dao.ValidationDao#checkUser(String)
     */
    @Override
    public void checkUser(String name) {
        getJdbcTemplate().queryForObject("select name from users where name = ?", new Object[]{name}, String.class);
    }

    /**
     * @see tk.jviewer.dao.ValidationDao#getUserPassword(String)
     */
    @Override
    public String getUserPassword(String name) {
        return getJdbcTemplate().queryForObject("select password from users where name = ?", new Object[]{name}, String.class);
    }
}
