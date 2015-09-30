package tk.jviewer.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;
import static org.apache.commons.lang3.StringUtils.trim;
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

    private long id;
    private String topic;
    private String text;
    private List<Answer> answers = new ArrayList<>();
    private Long correctSingleChoiceAnswer;
    private List<Long> correctMultipleChoiceAnswers = new ArrayList<>();
    private Long userSingleChoiceAnswer;
    private String correctTextualAnswer;
    private String userTextualAnswer;
    private List<String> userMultipleChoiceAnswers;
    private AnswerType typeOfAnswers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Question(final long id, final String text, final AnswerType typeOfAnswers) {
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

    public AnswerType getTypeOfAnswers() {
        return typeOfAnswers;
    }

    public void setTypeOfAnswers(AnswerType typeOfAnswers) {
        this.typeOfAnswers = typeOfAnswers;
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

    public void setAnswers(List<Answer> multipleChoiceAnswers) {
        this.answers = multipleChoiceAnswers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<Long> getCorrectMultipleChoiceAnswers() {
        return correctMultipleChoiceAnswers;
    }

    public void addCorrectMultipleChoiceAnswer(final Long answerId) {
        correctMultipleChoiceAnswers.add(answerId);
    }

    public void setCorrectMultipleChoiceAnswers(List<Long> correctMultipleChoiceAnswers) {
        this.correctMultipleChoiceAnswers = correctMultipleChoiceAnswers;
    }


    public String getCorrectTextualAnswer() {
        return correctTextualAnswer;
    }

    public void setCorrectTextualAnswer(String correctTextualAnswer) {
        this.correctTextualAnswer = correctTextualAnswer;
    }

    public String getUserTextualAnswer() {
        return userTextualAnswer;
    }

    public void setUserTextualAnswer(String userTextualAnswer) {
        this.userTextualAnswer = userTextualAnswer;
    }

    public Long getUserSingleChoiceAnswer() {
        return userSingleChoiceAnswer;
    }

    public void setUserSingleChoiceAnswer(Long userSingleChoiceAnswer) {
        this.userSingleChoiceAnswer = userSingleChoiceAnswer;
    }

    public List<String> getUserMultipleChoiceAnswers() {
        return userMultipleChoiceAnswers;
    }

    public void setUserMultipleChoiceAnswers(List<String> userMultipleChoiceAnswers) {
        this.userMultipleChoiceAnswers = userMultipleChoiceAnswers;
    }

    public boolean isCorrectlyAnswered() {
        if (typeOfAnswers == RADIO_BUTTON) {
            return correctSingleChoiceAnswer.equals(userSingleChoiceAnswer);
        } else if (typeOfAnswers == CHECK_BOX) {
            return isEqualCollection(correctMultipleChoiceAnswers, userMultipleChoiceAnswers);
        } else if (typeOfAnswers == TEXT_FIELD) {
            return correctTextualAnswer.equalsIgnoreCase(trim(userTextualAnswer));
        }

        throw new RuntimeException("Unsupported multipleChoiceAnswers type " + typeOfAnswers);
    }

    public Long getCorrectSingleChoiceAnswer() {
        return correctSingleChoiceAnswer;
    }

    public void setCorrectSingleChoiceAnswer(Long correctSingleChoiceAnswer) {
        this.correctSingleChoiceAnswer = correctSingleChoiceAnswer;
    }

    public void removeAnswer(long answerId) {
        answers.remove(new Answer(answerId)); // TODO
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return new EqualsBuilder()
                .append(id, question.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

}
