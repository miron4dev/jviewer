package tk.jviewer.model;

/**
 * Created by Sergey Yaskov on 01.10.2015.
 */
public class QuizManagedBean {

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
