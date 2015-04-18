package tk.jviewer.info.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.info.model.UserModel;

/**
 * Implementation of {@link UserDao}.
 */
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

    @Override
    public UserModel getUserByName(String name) throws EmptyResultDataAccessException {
        return getJdbcTemplate().queryForObject("select * from users where name = ?", new Object[]{name},
                new BeanPropertyRowMapper<>(UserModel.class));
    }
}
