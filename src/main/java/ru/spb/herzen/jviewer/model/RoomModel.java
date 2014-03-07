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

    /**
     * Gets current room id in database.
     * @return id
     */
    int getId();

    /**
     * Sets room id in database.
     * @param id of room
     */
    void setId(int id);

    /**
     * Gets current name of room.
     * @return name
     */
    String getName();

    /**
     * Sets current name of room.
     * @param name of room
     */
    void setName(String name);

    /**
     * Gets current password from room.
     * @return password
     */
    String getPassword();

    /**
     * Sets password for room.
     * @param password for room
     */
    void setPassword(String password);

    /**
     * Gets all rooms at the server.
     * @return list of rooms
     */
    List<String> getRooms();

    /**
     * Sets list of all rooms to the server.
     * @param rooms list of rooms
     */
    void setRooms(List<String> rooms);

    /**
     * Gets current chosen room.
     * @return current room.
     */
    String getCurrentRoom();

    /**
     * Sets current room.
     * @param currentRoom name of chosen room
     */
    void setCurrentRoom(String currentRoom);
}
