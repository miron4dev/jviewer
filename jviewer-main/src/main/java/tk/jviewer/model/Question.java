package tk.jviewer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Question representation.
 * @author Evgeny Mironenko
 */
public class Question implements Serializable {

    private static final long serialVersionUID = -1512807566188743676L;

    private int id;
    private String topic;
    private String text;
    private List<Answer> answers;
    private List<Integer> correctAnswers;
    private List<Integer> userAnswers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Integer> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<Integer> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public List<Integer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<Integer> userAnswers) {
        this.userAnswers = userAnswers;
    }

}
