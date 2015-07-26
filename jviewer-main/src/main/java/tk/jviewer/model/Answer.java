package tk.jviewer.model;

import java.io.Serializable;

/**
 * Answer representation.
 * @author Evgeny Mironenko
 */
public class Answer implements Serializable {

    private static final long serialVersionUID = -1283565263146494192L;

    private String text;
    private String correct;
    private AnswerType type;

    public Answer() {
    }

    public Answer(String text, String correct, AnswerType type) {
        this.text = text;
        this.correct = correct;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public AnswerType getType() {
        return type;
    }

    public void setType(AnswerType type) {
        this.type = type;
    }
}
