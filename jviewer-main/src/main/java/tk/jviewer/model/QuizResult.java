package tk.jviewer.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * QuizResult representation.
 *
 * @author Evgeny Mironenko
 */
public class QuizResult implements Serializable {

    private static final long serialVersionUID = 3736246668014217404L;

    private Integer id;
    private Map<Question, String> userAnswers = new HashMap<>();

    public QuizResult(final Map<Question, String> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Question, String> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(Map<Question, String> userAnswers) {
        this.userAnswers = userAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        QuizResult quizResult = (QuizResult) o;

        return new EqualsBuilder()
                .append(id, quizResult.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

}
