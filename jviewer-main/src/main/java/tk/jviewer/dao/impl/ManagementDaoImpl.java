package tk.jviewer.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.ManagementDao;
import tk.jviewer.model.Room;

import java.util.List;

/**
 * Management Data Access Object implementation.
 * @author Evgeny Mironenko
 */
public class ManagementDaoImpl extends JdbcDaoSupport implements ManagementDao {

    @Override
    public List<Room> getRooms() throws DataAccessException {
        return getJdbcTemplate().query("select * from rooms", new BeanPropertyRowMapper<>(Room.class));
    }

    @Override
    public void createRoom(String name, String password) throws DataAccessException {
        getJdbcTemplate().update("insert into rooms (name, password) values (?, ?)", name, password);
    }

    @Override
    public void deleteRoom(String name) throws DataAccessException {
        getJdbcTemplate().update("delete from rooms where name = ?", name);
    }
}
