package tk.jviewer.model;

import java.io.Serializable;

/**
 * Answer representation.
 *
 * @author Evgeny Mironenko
 */
public class Answer implements Serializable {

    private static final long serialVersionUID = -1283565263146494192L;

    private String id;
    private String text;
    private AnswerType type;

    public Answer() {
    }

    public Answer(String id, String text, AnswerType type) {
        this.id = id;
        this.text = text;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AnswerType getType() {
        return type;
    }

    public void setType(AnswerType type) {
        this.type = type;
    }
}
