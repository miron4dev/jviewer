package tk.jviewer.dialog;

import org.springframework.dao.DataAccessException;
import tk.jviewer.model.Room;
import tk.jviewer.controller.impl.ManagementController;
import tk.jviewer.model.UserModel;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Main page dialog.
 */
public class MainPageDialog {

    private static final List<String> POSSIBLE_ROOM_TYPES = Stream.of(Room.Type.values()).map(Enum::name).collect(Collectors.toList());

    private UserModel userModel;
    private ManagementController controller;

    public void createRoom(String name, String password, Room.Type type) {
        try {
            controller.createRoom(new Room(name, password, type));
        } catch (DataAccessException e) {
            //TODO
        }
    }

    public void deleteRoom(Room room) {
        try {
            controller.deleteRoom(room);
            addMessage("Success!", "Room has been successfully removed.");
        } catch (DataAccessException e) {
            //TODO
        }
    }

    public void chooseRoom(Room room) {
        userModel.setCurrentRoom(room.getName());
    }

    public List<Room> getAvailableRooms() {
        try {
            return controller.getRooms();
        } catch (DataAccessException e) {
            //TODO
            return new ArrayList<>();
        }
    }

    public List<String> getPossibleRoomTypes() {
        return POSSIBLE_ROOM_TYPES;
    }

    //
    // Helper methods
    //

    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //
    // Dependency Injection
    //

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setController(ManagementController controller) {
        this.controller = controller;
    }
}
