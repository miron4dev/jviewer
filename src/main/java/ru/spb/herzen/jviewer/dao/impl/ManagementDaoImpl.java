package ru.spb.herzen.jviewer.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import ru.spb.herzen.jviewer.dao.ManagementDao;

/**
 * Management Data Access Object implementation.
 * @author Evgeny Mironenko
 */
public class ManagementDaoImpl extends JdbcDaoSupport implements ManagementDao {

    /**
     * @see ru.spb.herzen.jviewer.dao.ManagementDao#createRoom(String, String)
     */
    @Override
    public boolean createRoom(String name, String password) {
        getJdbcTemplate().update("insert into rooms (name, password) values (?, ?)", name, password);
        return true;
    }

    /**
     * @see ru.spb.herzen.jviewer.dao.ManagementDao#removeRoom(String)
     */
    @Override
    public boolean removeRoom(String name) {
        getJdbcTemplate().update("delete from rooms where name = ?", name);
        return true;
    }
}
