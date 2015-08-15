package tk.jviewer.model;

import tk.jviewer.model.question.Question;

import java.io.Serializable;
import java.util.Map;

/**
 * Result representation.
 * @author Evgeny Mironenko
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 3736246668014217404L;

    private int id;
    private Map<Question, Boolean> question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Question, Boolean> getQuestion() {
        return question;
    }

    public void setQuestion(Map<Question, Boolean> question) {
        this.question = question;
    }

    /**
     * Returns the score of the current result in 100 percentage scale.
     * @return see description.
     */
    public int getScore() {
        return (int) (100 * question.values().stream().filter(aBoolean -> aBoolean).count()/question.size());
    }
}
