package tk.jviewer.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.ManagementDao;

/**
 * Management Data Access Object implementation.
 * @author Evgeny Mironenko
 */
public class ManagementDaoImpl extends JdbcDaoSupport implements ManagementDao {

    /**
     * @see tk.jviewer.dao.ManagementDao#createRoom(String, String)
     */
    @Override
    public boolean createRoom(String name, String password) {
        getJdbcTemplate().update("insert into rooms (name, password) values (?, ?)", name, password);
        return true;
    }

    /**
     * @see tk.jviewer.dao.ManagementDao#removeRoom(String)
     */
    @Override
    public boolean removeRoom(String name) {
        getJdbcTemplate().update("delete from rooms where name = ?", name);
        return true;
    }
}
