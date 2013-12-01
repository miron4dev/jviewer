package ru.spb.herzen.jviewer.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 3:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class ValidationDaoImpl extends JdbcDaoSupport implements ValidationDao {

    @Override
    public void checkUser(String name) throws EmptyResultDataAccessException{
        getJdbcTemplate().queryForObject("select name from user where name = ?", new Object[]{name}, String.class);
    }

    @Override
    public String getUserPassword(String name) throws EmptyResultDataAccessException{
        return getJdbcTemplate().queryForObject("select password from user where name = ?", new Object[]{name}, String.class);
    }
}
