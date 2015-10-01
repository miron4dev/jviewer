package tk.jviewer.dialog;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import tk.jviewer.model.QuizManagedBean;
import tk.jviewer.model.Room;
import tk.jviewer.controller.ManagementController;
import tk.jviewer.model.Test;
import tk.jviewer.model.ViewerManagedBean;
import tk.jviewer.profile.Permission;
import tk.jviewer.profile.UserProfile;
import tk.jviewer.service.QuizService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static tk.jviewer.profile.Permission.CREATE_ROOM;
import static tk.jviewer.profile.Permission.DELETE_ROOM;

/**
 * Main page dialog.
 */
public class MainPageDialog implements Serializable {

    private static final long serialVersionUID = -8729752510193178635L;
    private static final Logger LOG = Logger.getLogger(MainPageDialog.class);
    private static final List<String> POSSIBLE_ROOM_TYPES = Stream.of(Room.Type.values()).map(Enum::name).collect(Collectors.toList());

    private String roomName;
    private String roomPassword;
    private Room.Type roomType;

    private UserProfile userProfile;
    private ManagementController controller;
    private ViewerManagedBean viewerManagedBean;
    private QuizManagedBean quizManagedBean;

    private QuizService quizService;

    private List<Room> availableRooms;
    private List<Test> availableQuizzes;

    @PostConstruct
    public void init() {
        availableRooms = findAvailableRooms();
        availableQuizzes = findAvailableQuizzes();
    }

    public void createRoom() {
        try {
            controller.createRoom(new Room(roomName, roomPassword, roomType));
            addMessage(SEVERITY_INFO, "Success!", "Room has been successfully created.");
        } catch (DataAccessException e) {
            LOG.error(e);
            addMessage(SEVERITY_ERROR, "Failed", "Room has not been created, because of system error. Please refer to your system administrator");
        }
    }

    public void deleteRoom(Room room) {
        try {
            controller.deleteRoom(room);
            addMessage(SEVERITY_INFO, "Success!", "Room has been successfully removed.");
        } catch (DataAccessException e) {
            LOG.error(e);
            addMessage(SEVERITY_ERROR, "Failed", "Room has not been deleted, because of system error. Please refer to your system administrator");
        }
    }

    public void chooseRoom(Room room) {
        viewerManagedBean.setCurrentRoom(room);
    }

    public void chooseQuiz(final Test quiz) {
        quizManagedBean.setCurrentQuiz(quiz);
    }

    public void deleteQuiz(final Test quiz) {
        quizService.removeQuiz(quiz);
        addMessage(SEVERITY_INFO, "Success!", "Quiz has been successfully removed.");
    }

    public List<Room> getAvailableRooms() {
        return availableRooms;
    }

    public List<Test> getAvailableQuizzes() {
        return availableQuizzes;
    }

    public boolean isRoomCreationAllowed() {
        return userProfile.hasPermission(CREATE_ROOM);
    }

    public boolean isRoomDeletionAllowed() {
        return userProfile.hasPermission(DELETE_ROOM);
    }

    public List<String> getPossibleRoomTypes() {
        return POSSIBLE_ROOM_TYPES;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomPassword() {
        return roomPassword;
    }

    public void setRoomPassword(String roomPassword) {
        this.roomPassword = roomPassword;
    }

    public Room.Type getRoomType() {
        return roomType;
    }

    public void setRoomType(Room.Type roomType) {
        this.roomType = roomType;
    }

    //
    // Helper methods
    //

    private List<Room> findAvailableRooms() {
        try {
            return controller.getRooms();
        } catch (DataAccessException e) {
            LOG.error("Cannot find available rooms", e);
            addMessage(SEVERITY_ERROR, "Failed", "We can't retrieve the list of rooms, because of system error. Please refer to your system administrator");
            return new ArrayList<>();
        }
    }

    private List<Test> findAvailableQuizzes() {
        return quizService.findQuizzes();
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //
    // Dependency Injection
    //

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void setController(ManagementController controller) {
        this.controller = controller;
    }

    public void setViewerManagedBean(ViewerManagedBean viewerManagedBean) {
        this.viewerManagedBean = viewerManagedBean;
    }

    public void setQuizManagedBean(QuizManagedBean quizManagedBean) {
        this.quizManagedBean = quizManagedBean;
    }

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

}
