package tk.jviewer.dao;

import tk.jviewer.model.RoomModel;
import tk.jviewer.model.UserModel;

import java.util.List;

/**
 * Login Data Access Object interface.
 * @author Evgeny Mironenko
 */
public interface LoginDao {

    /**
     * Gets user data from database.
     * @param name name of user.
     * @return user data object.
     */
    UserModel getData(String name);

    /**
     * Gets list of all available rooms from database.
     * @return list of rooms.
     */
    List<RoomModel> getRooms();
}
