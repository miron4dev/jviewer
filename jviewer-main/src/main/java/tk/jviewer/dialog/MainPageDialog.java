package tk.jviewer.dialog;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import tk.jviewer.business.model.RoomEntity;
import tk.jviewer.model.Quiz;
import tk.jviewer.model.EditQuizManagedBean;
import tk.jviewer.model.TakeQuizManagedBean;
import tk.jviewer.model.ViewerManagedBean;
import tk.jviewer.security.SecurityService;
import tk.jviewer.business.api.ManagementService;
import tk.jviewer.business.api.QuizService;

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
import static javax.faces.application.FacesMessage.SEVERITY_WARN;
import static tk.jviewer.security.Permission.*;

/**
 * Main page dialog.
 */
public class MainPageDialog implements Serializable {

    private static final long serialVersionUID = -8729752510193178635L;
    private static final Logger LOG = Logger.getLogger(MainPageDialog.class);
    private static final List<String> POSSIBLE_ROOM_TYPES = Stream.of(RoomEntity.Type.values()).map(Enum::name).collect(Collectors.toList());

    private String roomName;
    private RoomEntity.Type roomType;
    private ManagementService managementService;

    private ViewerManagedBean viewerManagedBean;
    private EditQuizManagedBean editQuizManagedBean;
    private TakeQuizManagedBean takeQuizManagedBean;

    private QuizService quizService;

    private List<RoomEntity> availableRooms;
    private List<Quiz> availableQuizzes;

    /**
     * Init method for loading of available rooms and quizzes.
     */
    @PostConstruct
    public void init() {
        availableRooms = findAvailableRooms();
        availableQuizzes = findAvailableQuizzes();
    }

    /**
     * Creates new room.
     */
    public void createRoom() {
        try {
            if (isRoomExist(roomName)) {
                addMessage(SEVERITY_WARN, "Failed", "Room with specified name is already exist!");
            }
            RoomEntity newRoom = managementService.createRoom(roomName, roomType);
            availableRooms.add(newRoom);
            addMessage(SEVERITY_INFO, "Success!", "Room has been successfully created.");
        } catch (DataAccessException e) {
            LOG.error("Cannot create room", e);
            addMessage(SEVERITY_ERROR, "Failed", "Room has not been created, because of system error. Please refer to your system administrator");
        }
    }

    /**
     * Delets specified room.
     * @param room room for deletion.
     */
    public void deleteRoom(RoomEntity room) {
        try {
            managementService.deleteRoom(room);
            availableRooms.remove(room);
            addMessage(SEVERITY_INFO, "Success!", "Room has been successfully removed.");
        } catch (DataAccessException e) {
            LOG.error("Cannot delete room", e);
            addMessage(SEVERITY_ERROR, "Failed", "Room has not been deleted, because of system error. Please refer to your system administrator");
        }
    }

    /**
     * Goes to specified room.
     * @param room viewer room.
     * @return JSF navigation rule for Viewer page.
     */
    public String enterRoom(final RoomEntity room) {
        viewerManagedBean.setCurrentRoom(room);
        return "viewer?faces-redirect=true";
    }

    /**
     * Takes specified quiz.
     * @param quiz quiz for taking.
     * @return JSF navigation rule.
     */
    public String takeQuiz(final Quiz quiz) {
        takeQuizManagedBean.setChosenQuiz(quiz);
        return quiz.isAlreadyTaken() ? "quizresults?faces-redirect=true" : "takequiz?faces-redirect=true";
    }

    /**
     * Edits the specified quiz.
     * @param quiz quiz for edition.
     * @return JSf navigation rule.
     */
    public String editQuiz(final Quiz quiz) {
        editQuizManagedBean.setCurrentQuiz(quiz);
        return "createquiz?faces-redirect=true";
    }

    /**
     * Goes to Create Quiz dialog.
     * @return JSF navigation rule.
     */
    public String createQuiz() {
        editQuizManagedBean.setCurrentQuiz(null);
        return "createquiz?faces-redirect=true";
    }

    /**
     * Deletes specified quiz.
     * @param quiz quiz for deletion.
     */
    public void deleteQuiz(final Quiz quiz) {
        availableQuizzes.remove(quiz);
        quizService.removeQuiz(quiz);
        addMessage(SEVERITY_INFO, "Success!", "Quiz has been successfully removed.");
    }

    /**
     * Logs out from JViewer.
     *
     * @return see description.
     */
    public String logout() {
        return SecurityService.logout();
    }

    /**
     * Returns a list of all available rooms.
     * @return see description.
     */
    public List<RoomEntity> getAvailableRooms() {
        return availableRooms;
    }

    /**
     * Returns a list of all available quizzes.
     * @return see description.
     */
    public List<Quiz> getAvailableQuizzes() {
        return availableQuizzes;
    }

    /**
     * Returns a name of user.
     * @return see description.
     */
    public String getUsername() {
        return SecurityService.getUsername();
    }

    /**
     * Returns true if user is admin.
     * @return see description.
     */
    public boolean isAdmin() {
        return SecurityService.userHasPermission(ADMIN);
    }

    /**
     * Returns a list of all possible types of room.
     * @return see description.
     */
    public List<String> getPossibleRoomTypes() {
        return POSSIBLE_ROOM_TYPES;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public RoomEntity.Type getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomEntity.Type roomType) {
        this.roomType = roomType;
    }

    //
    // Helper methods
    //

    private List<RoomEntity> findAvailableRooms() {
        try {
            return managementService.getRooms();
        } catch (DataAccessException e) {
            LOG.error("Cannot find available rooms", e);
            addMessage(SEVERITY_ERROR, "Failed", "We can't retrieve the list of rooms, because of system error. Please refer to your system administrator");
            return new ArrayList<>();
        }
    }

    private List<Quiz> findAvailableQuizzes() {
        return quizService.findQuizzes();
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private boolean isRoomExist(String roomName) {
        for (RoomEntity room: availableRooms) {
            if (room.getName().equals(roomName)) {
                return true;
            }
        }
        return false;
    }

    //
    // Dependency Injection
    //

    public void setViewerManagedBean(ViewerManagedBean viewerManagedBean) {
        this.viewerManagedBean = viewerManagedBean;
    }

    public void setEditQuizManagedBean(EditQuizManagedBean editQuizManagedBean) {
        this.editQuizManagedBean = editQuizManagedBean;
    }

    public void setTakeQuizManagedBean(TakeQuizManagedBean takeQuizManagedBean) {
        this.takeQuizManagedBean = takeQuizManagedBean;
    }

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    public void setManagementService(ManagementService managementService) {
        this.managementService = managementService;
    }
}
