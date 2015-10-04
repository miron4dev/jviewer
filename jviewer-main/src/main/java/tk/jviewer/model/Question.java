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

    private Integer id;
    private String topic;
    private String text;
    private List<Answer> answers = new ArrayList<>();
    private Integer userSingleChoiceAnswer;
    private String correctTextualAnswer;
    private String userTextualAnswer;
    private List<Integer> userMultipleChoiceAnswers;
    private AnswerType typeOfAnswers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Question(final Integer id, final String text, final AnswerType typeOfAnswers) {
        this.id = id;
        this.text = text;
        this.typeOfAnswers = typeOfAnswers;
    }

    public Question(final Integer id, final String text, final AnswerType typeOfAnswers, final String correctTextualAnswer) {
        this.id = id;
        this.text = text;
        this.typeOfAnswers = typeOfAnswers;
        this.correctTextualAnswer = correctTextualAnswer;
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

    public Integer getCorrectSingleChoiceAnswer() {
        for (final Answer answer : answers) {
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

        for (final Answer answer : answers) {
            answer.setCorrect(answer.getId() == correctSingleChoiceAnswer.intValue());
        }
    }

    public List<Integer> getCorrectMultipleChoiceAnswers() {
        final List<Integer> correct = new ArrayList<>();
        for (final Answer answer : answers) {
            if (answer.isCorrect()) {
                correct.add(answer.getId());
            }
        }
        return correct;
    }

    public void setCorrectMultipleChoiceAnswers(List<Integer> correctMultipleChoiceAnswers) {
        for (final Answer answer : answers) {
            answer.setCorrect(correctMultipleChoiceAnswers.contains(answer.getId()));
        }
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

    public Integer getUserSingleChoiceAnswer() {
        return userSingleChoiceAnswer;
    }

    public void setUserSingleChoiceAnswer(Integer userSingleChoiceAnswer) {
        this.userSingleChoiceAnswer = userSingleChoiceAnswer;
    }

    public List<Integer> getUserMultipleChoiceAnswers() {
        return userMultipleChoiceAnswers;
    }

    public void setUserMultipleChoiceAnswers(List<Integer> userMultipleChoiceAnswers) {
        this.userMultipleChoiceAnswers = userMultipleChoiceAnswers;
    }

    public boolean isCorrectlyAnswered() {
        if (typeOfAnswers == RADIO_BUTTON) {
            final Integer correctSingleChoiceAnswer = getCorrectSingleChoiceAnswer();
            return correctSingleChoiceAnswer.equals(userSingleChoiceAnswer);
        } else if (typeOfAnswers == CHECK_BOX) {
            final List<Integer> correctMultipleChoiceAnswers = getCorrectMultipleChoiceAnswers();
            return isEqualCollection(correctMultipleChoiceAnswers, userMultipleChoiceAnswers);
        } else if (typeOfAnswers == TEXT_FIELD) {
            return correctTextualAnswer.equalsIgnoreCase(trim(userTextualAnswer));
        }

        throw new RuntimeException("Unsupported multipleChoiceAnswers type " + typeOfAnswers);
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
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

    public static Question lookupById(final List<Question> questions, final Integer id) {
        for (final Question question : questions) {
            if (question.getId().equals(id)) {
                return question;
            }
        }

        throw new RuntimeException("No question with id " + id + " found");
    }

}
