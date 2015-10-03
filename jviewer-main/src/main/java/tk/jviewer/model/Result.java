package tk.jviewer.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * Result representation.
 *
 * @author Evgeny Mironenko
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 3736246668014217404L;

    private Integer id;
    private Map<Question, Boolean> question;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
     *
     * @return see description.
     */
    public int getScore() {
        return (int) (100 * question.values().stream().filter(aBoolean -> aBoolean).count() / question.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        return new EqualsBuilder()
                .append(id, result.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

}
