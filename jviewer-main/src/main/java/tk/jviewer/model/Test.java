package tk.jviewer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Test representation.
 * @author Evgeny Mironenko
 */
public class Test implements Serializable {

    private static final long serialVersionUID = 3480658726164313166L;

    private String name;
    private List<Question> questions;
    private Question currentQuestion;

    public Test(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
        this.currentQuestion = questions.get(0);
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public Integer getProgress() {
        return 0;
    }
}
