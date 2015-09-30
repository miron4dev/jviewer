package tk.jviewer.model;

/**
 * Created by Sergey Yaskov on 01.10.2015.
 */
public class QuizManagedBean {

    private Test currentQuiz;

    private Question editingQuestion;

    public Test getCurrentQuiz() {
        return currentQuiz;
    }

    public void setCurrentQuiz(Test currentQuiz) {
        this.currentQuiz = currentQuiz;
    }

    public Question getEditingQuestion() {
        return editingQuestion;
    }

    public void setEditingQuestion(Question editingQuestion) {
        this.editingQuestion = editingQuestion;
    }

}
