package tk.jviewer.model;

import java.io.Serializable;

/**
 * Contains Edit Quiz dialog state.
 */
public class EditQuizManagedBean implements Serializable {

    private static final long serialVersionUID = 7478910365189392773L;

    private Quiz currentQuiz;

    private Question editingQuestion;

    public Quiz getCurrentQuiz() {
        return currentQuiz;
    }

    public void setCurrentQuiz(Quiz currentQuiz) {
        this.currentQuiz = currentQuiz;
    }

    public Question getEditingQuestion() {
        return editingQuestion;
    }

    public void setEditingQuestion(Question editingQuestion) {
        this.editingQuestion = editingQuestion;
    }

}
