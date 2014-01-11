package ru.spb.herzen.jviewer.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeny
 * Date: 1/11/14
 * Time: 6:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagementDaoImpl extends JdbcDaoSupport implements ManagementDao {

    @Override
    public void createRoom(String name, String password) throws DataAccessException {
        getJdbcTemplate().update("insert into rooms (name, password) values (?, ?)", name, password);
    }

    @Override
    public void removeRoom(String name) throws DataAccessException {
        getJdbcTemplate().update("delete from rooms where name = ?", name);
    }
}
