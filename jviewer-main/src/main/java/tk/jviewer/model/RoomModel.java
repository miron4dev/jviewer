package tk.jviewer.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import tk.jviewer.service.LoginService;
import tk.jviewer.service.ManagementService;

import java.util.ArrayList;
import java.util.List;

/**
 * Room model implementation.
 *
 * @author Evgeny Mironenko
 */
public class RoomModel {

    private int id;
    private String name;
    private String password;
    private List<String> rooms;
    private String currentRoom;

    private ManagementService managementService;
    private LoginService loginService;

    public String createRoom() {
        managementService.createRoom(name, password);
        refreshRooms();
        return "admin?faces-redirect=true";
    }

    public String removeRoom() {
        managementService.removeRoom(currentRoom);
        refreshRooms();
        return "admin?faces-redirect=true";
    }

    public void refreshRooms() {
        rooms = loadNames(loginService.getRooms());
        if (!rooms.isEmpty()) {
            currentRoom = rooms.get(0);
        } else {
            currentRoom = "";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RoomModel) {
            RoomModel room = (RoomModel) obj;
            return id == room.getId() && StringUtils.equals(name, room.getName()) && StringUtils.equals(password, room.getPassword()) &&
                    StringUtils.equals(currentRoom, room.getCurrentRoom());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).append(password).append(currentRoom).build();
    }

    /**
     * Loads the list of room names.
     *
     * @param roomModelList list of room objects.
     * @return list of room names.
     */
    private List<String> loadNames(List<RoomModel> roomModelList) {
        List<String> names = new ArrayList<>();
        for (RoomModel aRoomModelList : roomModelList) {
            names.add(aRoomModelList.getName());
        }

        return names;
    }

    //
    // Getters and setters.
    //

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public List<String> getRooms() {
        return rooms;
    }


    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }


    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    //
    // Dependency Injection
    //

    public void setManagementService(ManagementService managementService) {
        this.managementService = managementService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
