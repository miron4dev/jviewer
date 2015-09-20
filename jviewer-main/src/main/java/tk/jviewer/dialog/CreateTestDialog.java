package tk.jviewer.dialog;

import tk.jviewer.model.Question;

import javax.annotation.PostConstruct;
import java.io.Serializable;

import static tk.jviewer.model.AnswerType.CHECK_BOX;

/**
 * Serves "create test" use case.
 */
public class CreateTestDialog implements Serializable {

    private static final long serialVersionUID = -2334735949960107078L;

    private Question currentQuestion;

    @PostConstruct
    public void init() {
        currentQuestion = new Question(CHECK_BOX);
    }

    public String cancelTestCreation() {
        return "main?faces-redirect=true";
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

}
