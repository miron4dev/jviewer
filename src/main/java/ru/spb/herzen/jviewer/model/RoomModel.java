package ru.spb.herzen.jviewer.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeny
 * Date: 1/7/14
 * Time: 1:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RoomModel {

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
    DisplayModel getDisplayModel();
    void setDisplayModel(DisplayModel displayModel);
    String createRoom();
    String removeRoom();
    void refreshRooms();
}
