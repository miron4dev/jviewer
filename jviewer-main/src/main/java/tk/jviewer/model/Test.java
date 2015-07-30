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
    private int currentQuestionIndex;

    public Test(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public void nextQuestion() {
        currentQuestionIndex++;
    }

    public Integer getProgress() {
        return 0;
    }
}
