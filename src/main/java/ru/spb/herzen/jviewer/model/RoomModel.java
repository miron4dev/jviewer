package ru.spb.herzen.jviewer.model;

import java.util.List;

/**
 * Room model interface.
 * @author Evgeny Mironenko
 */
public interface RoomModel {

    /**
     * Creates new room.
     * @return URL to admin page menu.
     */
    String createRoom();

    /**
     * Removes chosen room.
     * @return URL to admin page menu.
     */
    String removeRoom();

    /**
     * Refreshes list of rooms.
     */
    void refreshRooms();

    //
    // Getters and setters.
    //
    int getId();
    void setId(int id);
    String getName();
    void setName(String name);
    String getPassword();
    void setPassword(String password);
    List<String> getRooms();
    void setRooms(List<String> rooms);
    String getCurrentRoom();
    void setCurrentRoom(String currentRoom);
}
