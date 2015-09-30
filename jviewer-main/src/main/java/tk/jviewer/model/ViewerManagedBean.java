package tk.jviewer.model;

/**
 * Contains state of the Viewer dialog.
 */
public class ViewerManagedBean {

    private Room currentRoom;
    private Test currentQuiz;

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Test getCurrentQuiz() {
        return currentQuiz;
    }

    public void setCurrentQuiz(Test currentQuiz) {
        this.currentQuiz = currentQuiz;
    }
}
