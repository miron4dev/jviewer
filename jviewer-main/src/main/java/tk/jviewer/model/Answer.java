package tk.jviewer.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Answer representation.
 *
 * @author Evgeny Mironenko
 */
public class Answer implements Serializable {

    private static final long serialVersionUID = -1283565263146494192L;

    private long id; // TODO: primitive or object?
    private String text;
    private boolean correct;

    public Answer() {
    }

    public Answer(long id) {
        this.id = id;
    }

    public Answer(String text) {
        this.text = text;
    }

    public Answer(long id, String text) {
        this(id, text, false);
    }

    public Answer(long id, String text, boolean correct) {
        this.id = id;
        this.text = text;
        this.correct = correct;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        return new EqualsBuilder()
                .append(id, answer.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}
