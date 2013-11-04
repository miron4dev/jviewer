package ru.spb.herzen.jviewer.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import ru.spb.herzen.jviewer.model.UserModel;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationDaoImpl extends JdbcDaoSupport implements RegistrationDao {

    @Override
    public void regProfile(UserModel userModel) {
        String sql = "insert into user (name, password) values (?, ?)";
        getJdbcTemplate().update(sql, userModel.getName(), userModel.getPassword());
    }
}
