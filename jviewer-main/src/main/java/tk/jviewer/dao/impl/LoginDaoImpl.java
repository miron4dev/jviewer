package tk.jviewer.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.LoginDao;
import tk.jviewer.model.UserModel;

/**
 * Login Data Access Object implementation.
 * @author Evgeny Mironenko
 */
public class LoginDaoImpl extends JdbcDaoSupport implements LoginDao {

    @Override
    public UserModel getData(String name) {
        return getJdbcTemplate().queryForObject("select * from users where name = ?", new Object[]{name},
                new BeanPropertyRowMapper<>(UserModel.class));
    }
}
