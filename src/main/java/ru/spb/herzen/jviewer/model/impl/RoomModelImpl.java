package ru.spb.herzen.jviewer.model.impl;

import ru.spb.herzen.jviewer.model.DisplayModel;
import ru.spb.herzen.jviewer.model.RoomModel;
import ru.spb.herzen.jviewer.model.SystemModel;
import ru.spb.herzen.jviewer.service.LoginService;
import ru.spb.herzen.jviewer.service.ManagementService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.spb.herzen.jviewer.model.impl.CommonModel.equalsString;

/**
 * Room model implementation.
 * @author Evgeny Mironenko
 */
public class RoomModelImpl implements RoomModel {

    private int id;
    private String name;
    private String password;
    private List<String> rooms;
    private String currentRoom;
    private SystemModel systemModel;

    private ManagementService managementService;
    private LoginService loginService;

    /**
     * @see ru.spb.herzen.jviewer.model.RoomModel#createRoom()
     */
    @Override
    public String createRoom() {
        managementService.createRoom(name, password);
        refreshRooms();
        return "admin?faces-redirect=true";
    }

    /**
     * @see ru.spb.herzen.jviewer.model.RoomModel#removeRoom()
     */
    @Override
    public String removeRoom() {
        managementService.removeRoom(currentRoom);
        refreshRooms();
        return "admin?faces-redirect=true";
    }

    /**
     * @see ru.spb.herzen.jviewer.model.RoomModel#refreshRooms()
     */
    @Override
    public void refreshRooms(){
        Map<String, DisplayModel> map = new HashMap<>();
        rooms = loadNames(loginService.getRooms());
        for(String room: rooms){
            map.put(room, new DisplayModelImpl());
        }
        systemModel.setCurrentState(map);
        if(rooms.size() != 0){
            currentRoom = rooms.get(0);
        }
        else {
            currentRoom = "";
        }
    }

    /**
     * Loads the list of room names.
     * @param roomModelList list of room objects.
     * @return list of room names.
     */
    private List<String> loadNames(List<RoomModelImpl> roomModelList){
        List<String> names = new ArrayList<>();
        for (RoomModelImpl aRoomModelList : roomModelList) {
            names.add(aRoomModelList.getName());
        }

        return names;
    }



    @Override
    public boolean equals(Object obj) {
        if(obj instanceof RoomModelImpl) {
            RoomModelImpl room = (RoomModelImpl)obj;
            return id == room.getId() && equalsString(name, room.getName()) && equalsString(password, room.getPassword()) &&
                    equalsString(currentRoom, room.getCurrentRoom());
        }
        return false;
    }

    //
    // Getters and setters.
    //

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
    public List<String> getRooms() {
        return rooms;
    }

    @Override
    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String getCurrentRoom() {
        return currentRoom;
    }

    @Override
    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setManagementService(ManagementService managementService) {
        this.managementService = managementService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public void setSystemModel(SystemModel systemModel) {
        this.systemModel = systemModel;
    }
}
