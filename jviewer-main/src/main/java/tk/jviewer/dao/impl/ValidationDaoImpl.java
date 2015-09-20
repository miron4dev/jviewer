package tk.jviewer.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.ValidationDao;

/**
 * Validation Data Access Object implementation.
 *
 * @author Evgeny Mironenko
 */
public class ValidationDaoImpl extends JdbcDaoSupport implements ValidationDao {

    /**
     * @see tk.jviewer.dao.ValidationDao#checkUser(String)
     */
    @Override
    public void checkUser(final String username) {
        final String query = "select username from users where username = ?";
        getJdbcTemplate().queryForObject(query, new Object[]{username}, String.class);
    }

    /**
     * @see tk.jviewer.dao.ValidationDao#getUserPassword(String)
     */
    @Override
    public String getUserPassword(final String username) {
        final String query = "select password from users where username = ?";
        return getJdbcTemplate().queryForObject(query, new Object[]{username}, String.class);
    }

}
