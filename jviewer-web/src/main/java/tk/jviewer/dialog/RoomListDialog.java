package tk.jviewer.dialog;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;
import tk.jviewer.business.model.RoomEntity;
import tk.jviewer.business.api.RoomService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

/**
 * Room List dialog backing bean.
 */
public class RoomListDialog implements Serializable {

    private static final long serialVersionUID = 8681579858580912746L;
    private static final Logger logger = Logger.getLogger(MainDialog.class);
    private static final List<String> POSSIBLE_ROOM_TYPES = Stream.of(RoomEntity.Type.values()).map(Enum::name).collect(Collectors.toList());

    private List<RoomEntity> availableRooms;

    private RoomService roomService;

    /**
     * Init method for loading of available rooms.
     */
    @PostConstruct
    public void init() {
        availableRooms = retrieveAvailableRooms();
    }


    /**
     * Returns the list of available rooms.
     * @return see description.
     */
    public List<RoomEntity> getAvailableRooms() {
        return availableRooms;
    }

    /**
     * Returns a list of all possible Room types.
     * @return see description.
     */
    public List<String> getPossibleRoomTypes() {
        return POSSIBLE_ROOM_TYPES;
    }

    /**
     * Closes the dialog with the chosen room.
     * @param room viewer room.
     */
    public void chooseRoom(RoomEntity room) {
        RequestContext.getCurrentInstance().closeDialog(room);
    }

    //
    // Helper methods
    //

    /**
     * Retrieves the list of available rooms.
     *
     * @return see description.
     */
    private List<RoomEntity> retrieveAvailableRooms() {
        try {
            return roomService.getRooms();
        } catch (DataAccessException e) {
            logger.error("Cannot find available rooms", e);
            addMessage(SEVERITY_ERROR, "Failed", "We can't retrieve the list of rooms, because of system error. Please refer to your system administrator");
            return new ArrayList<>();
        }
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //
    // Dependency Injection
    //

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

}
