package ru.spb.herzen.jviewer.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import ru.spb.herzen.jviewer.dao.LoginDao;
import ru.spb.herzen.jviewer.model.impl.RoomModelImpl;
import ru.spb.herzen.jviewer.model.impl.UserModelImpl;
import ru.spb.herzen.jviewer.model.UserModel;

import java.util.List;

/**
 * Login Data Access Object implementation.
 * @author Evgeny Mironenko
 */
public class LoginDaoImpl extends JdbcDaoSupport implements LoginDao {

    /**
     * @see ru.spb.herzen.jviewer.dao.LoginDao#getData(String)
     */
    @Override
    public UserModel getData(String name) throws EmptyResultDataAccessException{
        return getJdbcTemplate().queryForObject("select * from users where name = ?", new Object[]{name},
                new BeanPropertyRowMapper<>(UserModelImpl.class));
    }

    /**
     * @see ru.spb.herzen.jviewer.dao.LoginDao#getRooms()
     */
    @Override
    public List<RoomModelImpl> getRooms() {
        return getJdbcTemplate().query("select * from rooms", new BeanPropertyRowMapper<>(RoomModelImpl.class));
    }
}
