package ru.spb.herzen.jviewer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeny
 * Date: 1/7/14
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoomModelImpl implements RoomModel, Serializable {

    private int id;
    private String name;
    private String password;
    private List<String> rooms;
    private String currentRoom;

    @Override
    public String addRoom() {
        //TODO
        return null;
    }

    @Override
    public String removeRoom() {
        //TODO
        return null;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public List getRooms() {
        return rooms;
    }

    @Override
    public void setRooms(List rooms) {
        this.rooms = rooms;
    }

    @Override
    public String getCurrentRoom() {
        if(rooms != null){
            currentRoom = rooms.get(0);
        }
        return currentRoom == null ? "" : currentRoom;
    }

    @Override
    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }
}
