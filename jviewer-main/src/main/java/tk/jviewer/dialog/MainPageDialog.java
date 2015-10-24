package tk.jviewer.dialog;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import tk.jviewer.model.Quiz;
import tk.jviewer.model.EditQuizManagedBean;
import tk.jviewer.model.Room;
import tk.jviewer.controller.ManagementController;
import tk.jviewer.model.TakeQuizManagedBean;
import tk.jviewer.model.ViewerManagedBean;
import tk.jviewer.security.SecurityService;
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
import static tk.jviewer.security.Permission.*;

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
    private ManagementController controller;

    private ViewerManagedBean viewerManagedBean;
    private EditQuizManagedBean editQuizManagedBean;
    private TakeQuizManagedBean takeQuizManagedBean;

    private QuizService quizService;

    private List<Room> availableRooms;
    private List<Quiz> availableQuizzes;

    @PostConstruct
    public void init() {
        availableRooms = findAvailableRooms();
        availableQuizzes = findAvailableQuizzes();
    }

    public void createRoom() {
        try {
            Room newRoom = new Room(roomName, roomPassword ,roomType);
            controller.createRoom(newRoom);
            availableRooms.add(newRoom);
            addMessage(SEVERITY_INFO, "Success!", "Room has been successfully created.");
        } catch (DataAccessException e) {
            LOG.error("Cannot create room", e);
            addMessage(SEVERITY_ERROR, "Failed", "Room has not been created, because of system error. Please refer to your system administrator");
        }
    }

    public void deleteRoom(Room room) {
        try {
            availableRooms.remove(room);
            controller.deleteRoom(room);
            addMessage(SEVERITY_INFO, "Success!", "Room has been successfully removed.");
        } catch (DataAccessException e) {
            LOG.error("Cannot delete room", e);
            addMessage(SEVERITY_ERROR, "Failed", "Room has not been deleted, because of system error. Please refer to your system administrator");
        }
    }

    public String enterRoom(final Room room) {
        viewerManagedBean.setCurrentRoom(room);
        return "viewer?faces-redirect=true";
    }

    public String takeQuiz(final Quiz quiz) {
        takeQuizManagedBean.setChosenQuiz(quiz);
        return quiz.isAlreadyTaken() ? "quizresults?faces-redirect=true" : "takequiz?faces-redirect=true";
    }

    public String editQuiz(final Quiz quiz) {
        editQuizManagedBean.setCurrentQuiz(quiz);
        return "createquiz?faces-redirect=true";
    }

    public String createQuiz() {
        editQuizManagedBean.setCurrentQuiz(null);
        return "createquiz?faces-redirect=true";
    }

    public void deleteQuiz(final Quiz quiz) {
        availableQuizzes.remove(quiz);
        quizService.removeQuiz(quiz);
        addMessage(SEVERITY_INFO, "Success!", "Quiz has been successfully removed.");
    }

    public List<Room> getAvailableRooms() {
        return availableRooms;
    }

    public List<Quiz> getAvailableQuizzes() {
        return availableQuizzes;
    }

    public boolean isRoomCreationAllowed() {
        return SecurityService.userHasPermission(CREATE_ROOM);
    }

    public boolean isRoomDeletionAllowed() {
        return SecurityService.userHasPermission(DELETE_ROOM);
    }

    public boolean isQuizCreationAllowed() {
        return SecurityService.userHasPermission(CREATE_QUIZ);
    }

    public boolean isQuizEditionAllowed() {
        return SecurityService.userHasPermission(EDIT_QUIZ);
    }

    public boolean isQuizDeletionAllowed() {
        return SecurityService.userHasPermission(DELETE_QUIZ);
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

    private List<Quiz> findAvailableQuizzes() {
        return quizService.findQuizzes();
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //
    // Dependency Injection
    //

    public void setController(ManagementController controller) {
        this.controller = controller;
    }

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

}
