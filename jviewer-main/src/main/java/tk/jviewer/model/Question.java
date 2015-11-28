package tk.jviewer.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;
import static org.apache.commons.lang3.StringUtils.trim;
import static org.springframework.util.Assert.state;
import static tk.jviewer.model.AnswerType.CHECK_BOX;
import static tk.jviewer.model.AnswerType.RADIO_BUTTON;
import static tk.jviewer.model.AnswerType.TEXT_FIELD;

/**
 * Question representation.
 *
 * @author Evgeny Mironenko
 */
public class Question implements Serializable {

    private static final long serialVersionUID = -1512807566188743676L;

    private Integer id;
    private String text;
    private AnswerType typeOfAnswers;
    private List<Answer> possibleAnswers = new ArrayList<>();
    private List<Answer> userAnswers = new ArrayList<>();

    public Question(final Integer id, final String text, final AnswerType typeOfAnswers) {
        this.id = id;
        this.text = text;
        this.typeOfAnswers = typeOfAnswers;
    }

    public Question(final String text, final AnswerType typeOfAnswers) {
        this.text = text;
        this.typeOfAnswers = typeOfAnswers;
    }

    public Question(final AnswerType typeOfAnswers) {
        this.typeOfAnswers = typeOfAnswers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AnswerType getTypeOfAnswers() {
        return typeOfAnswers;
    }

    public void setTypeOfAnswers(AnswerType typeOfAnswers) {
        this.typeOfAnswers = typeOfAnswers;
    }

    public List<Answer> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<Answer> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public void addPossibleAnswer(Answer possibleAnswer) {
        possibleAnswers.add(possibleAnswer);
    }

    public void removeAnswer(Answer answer) {
        possibleAnswers.remove(answer);
    }

    public List<Answer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<Answer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public Integer getCorrectSingleChoiceAnswer() {
        for (final Answer answer : possibleAnswers) {
            if (answer.isCorrect()) {
                return answer.getId();
            }
        }
        return null;
    }

    public void setCorrectSingleChoiceAnswer(Integer correctSingleChoiceAnswer) {
        if (correctSingleChoiceAnswer == null) {
            return;
        }

        for (final Answer answer : possibleAnswers) {
            answer.setCorrect(answer.getId() == correctSingleChoiceAnswer.intValue());
        }
    }

    public List<Integer> getCorrectMultipleChoiceAnswers() {
        final List<Integer> correct = new ArrayList<>();
        for (final Answer answer : possibleAnswers) {
            if (answer.isCorrect()) {
                correct.add(answer.getId());
            }
        }
        return correct;
    }

    public void setCorrectMultipleChoiceAnswers(List<Integer> correctMultipleChoiceAnswers) {
        for (final Answer answer : possibleAnswers) {
            answer.setCorrect(correctMultipleChoiceAnswers.contains(answer.getId()));
        }
    }

    public String getCorrectTextualAnswer() {
        return getTheOnlyPossibleAnswer().getText();
    }

    public void setCorrectTextualAnswer(String correctTextualAnswer) {
        getTheOnlyPossibleAnswer().setText(correctTextualAnswer);
    }

    public boolean isCorrectlyAnswered() {
        return getOnlyCorrectAnswers().equals(userAnswers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return new EqualsBuilder()
                .append(text, question.text)
                .append(typeOfAnswers, question.typeOfAnswers)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(text)
                .append(typeOfAnswers)
                .toHashCode();
    }

    public static Question lookupById(final List<Question> questions, final Integer id) {
        for (final Question question : questions) {
            if (question.getId().equals(id)) {
                return question;
            }
        }

        throw new RuntimeException("No question with id " + id + " found");
    }

    //
    // Helper Methods
    //

    private Answer getTheOnlyPossibleAnswer() {
        return possibleAnswers.get(0);
    }

    private List<Answer> getOnlyCorrectAnswers() {
        final List<Answer> correctAnswers = new ArrayList<>(possibleAnswers.size());
        for (Answer possibleAnswer : possibleAnswers) {
            if (possibleAnswer.isCorrect()) {
                correctAnswers.add(possibleAnswer);
            }
        }
        return correctAnswers;
    }

}
