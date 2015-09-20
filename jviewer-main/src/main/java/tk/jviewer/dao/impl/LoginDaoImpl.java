package tk.jviewer.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.LoginDao;
import tk.jviewer.profile.UserProfile;

/**
 * Login Data Access Object implementation.
 * @author Evgeny Mironenko
 */
public class LoginDaoImpl extends JdbcDaoSupport implements LoginDao {

    @Override
    public UserProfile getData(String username) {
        return getJdbcTemplate().queryForObject("select * from users where username = ?", new Object[]{username},
                new BeanPropertyRowMapper<>(UserProfile.class));
    }
}
