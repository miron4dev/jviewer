package ru.spb.herzen.jviewer.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Validation Data Access Object implementation.
 * @author Evgeny Mironenko
 */
public class ValidationDaoImpl extends JdbcDaoSupport implements ValidationDao {

    /**
     * @see  ru.spb.herzen.jviewer.dao.ValidationDao#checkUser(String)
     */
    @Override
    public void checkUser(String name) throws EmptyResultDataAccessException{
        getJdbcTemplate().queryForObject("select name from users where name = ?", new Object[]{name}, String.class);
    }

    /**
     * @see ru.spb.herzen.jviewer.dao.ValidationDao#getUserPassword(String)
     */
    @Override
    public String getUserPassword(String name) throws EmptyResultDataAccessException{
        return getJdbcTemplate().queryForObject("select password from users where name = ?", new Object[]{name}, String.class);
    }
}
