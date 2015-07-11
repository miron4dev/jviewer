package tk.jviewer.model;

import java.util.List;

/**
 * Test representation.
 * @author Evgeny Mironenko
 */
public class Test {

    private List<Question> questions;
    private Question currentQuestion;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
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
