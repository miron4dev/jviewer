package ru.spb.herzen.jviewer.dao.impl;

import org.springframework.dao.DataAccessException;
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
    public void createRoom(String name, String password) throws DataAccessException {
        getJdbcTemplate().update("insert into rooms (name, password) values (?, ?)", name, password);
    }

    /**
     * @see ru.spb.herzen.jviewer.dao.ManagementDao#removeRoom(String)
     */
    @Override
    public void removeRoom(String name) throws DataAccessException {
        getJdbcTemplate().update("delete from rooms where name = ?", name);
    }
}
