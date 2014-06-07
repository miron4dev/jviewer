package ru.spb.herzen.jviewer.dao;

import ru.spb.herzen.jviewer.model.impl.RoomModelImpl;
import ru.spb.herzen.jviewer.model.UserModel;

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
    List<RoomModelImpl> getRooms();
}
