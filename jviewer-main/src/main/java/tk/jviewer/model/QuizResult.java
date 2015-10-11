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

    private final Question question;
    private final String correctAnswers;
    private final String userAnswers;

    public QuizResult(Question question, String correctAnswers, String userAnswers) {
        this.question = question;
        this.correctAnswers = correctAnswers;
        this.userAnswers = userAnswers;
    }

    public Question getQuestion() {
        return question;
    }

    public String getCorrectAnswers() {
        return correctAnswers;
    }

    public String getUserAnswers() {
        return userAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        QuizResult that = (QuizResult) o;

        return new EqualsBuilder()
                .append(question, that.question)
                .append(correctAnswers, that.correctAnswers)
                .append(userAnswers, that.userAnswers)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(question)
                .append(correctAnswers)
                .append(userAnswers)
                .toHashCode();
    }

}
