package ru.spb.herzen.jviewer.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.spb.herzen.jviewer.model.RoomModelImpl;
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
     * @throws EmptyResultDataAccessException if user is not exist.
     */
    UserModel getData(String name) throws EmptyResultDataAccessException;

    /**
     * Gets list of all available rooms from database.
     * @return list of rooms.
     */
    List<RoomModelImpl> getRooms();
}
