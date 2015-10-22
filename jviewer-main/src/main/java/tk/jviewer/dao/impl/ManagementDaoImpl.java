package tk.jviewer.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.ManagementDao;
import tk.jviewer.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Management Data Access Object implementation.
 *
 * @author Evgeny Mironenko
 */
public class ManagementDaoImpl extends JdbcDaoSupport implements ManagementDao {

    @Override
    public List<Room> getRooms() throws DataAccessException {
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList("select id, name, password, type from \"jviewer_main\".\"room\"");
        List<Room> rooms = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            final Room room = new Room(get(row, "name"), get(row, "password"), Room.Type.valueOf(get(row, "type")));
            room.setId((Integer) row.get("id"));
            rooms.add(room);
        }
        return rooms;
    }

    @Override
    public void createRoom(String name, String password, String type) throws DataAccessException {
        getJdbcTemplate().update("insert into \"jviewer_main\".\"room\" (name, password, type) values (?, ?, ?)", name, password, type);
    }

    @Override
    public void deleteRoom(String name) throws DataAccessException {
        getJdbcTemplate().update("delete from \"jviewer_main\".\"room\" where name = ?", name);
    }

    //
    // Helper Methods
    //

    private static String get(final Map<String, Object> row, final String column) {
        return (String) row.get(column);
    }

}
