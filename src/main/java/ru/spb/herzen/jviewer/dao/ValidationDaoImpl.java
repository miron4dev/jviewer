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
    public boolean checkUser(String name) {
        String sql = "select name from user where name = ?";
        try{
            String currentName = getJdbcTemplate().queryForObject(sql, new Object[]{name}, String.class);
            if(name.equals(currentName)){
                return false;
            }
        }
        catch (EmptyResultDataAccessException e){
            return true;
        }
        return true;
    }

    @Override
    public boolean validateData(String name, String password) {
        String sql = "select password from user where name = ?";
        try{
            String currentPassword = getJdbcTemplate().queryForObject(sql, new Object[]{name}, String.class);
            if(password.equals(currentPassword)){
                return true;
            }
        }
        catch (EmptyResultDataAccessException e){
            return false;
        }
        return false;
    }
}
