package tk.jviewer.model;

import java.io.Serializable;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Contains Take Quiz dialog state.
 */
public class TakeQuizManagedBean implements Serializable {

    private static final long serialVersionUID = -413311231242850151L;

    private Quiz chosenQuiz;
    private List<Quiz> availableQuizzes;

    public Quiz getChosenQuiz() {
        return chosenQuiz;
    }

    public void setChosenQuiz(Quiz chosenQuiz) {
        this.chosenQuiz = chosenQuiz;
    }

    public List<Quiz> getAvailableQuizzes() {
        return availableQuizzes;
    }

    public void setAvailableQuizzes(List<Quiz> availableQuizzes) {
        this.availableQuizzes = availableQuizzes;
    }

    public Quiz lookupTestByName(final String name) {
        if (isEmpty(name)) {
            return null;
        }
        for (final Quiz quiz : availableQuizzes) {
            if (name.equals(quiz.getName())) {
                return quiz;
            }
        }
        return null;
    }

}
