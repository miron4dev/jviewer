package tk.jviewer.model;

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
    private String correctSingleChoiceAnswer;
    private List<String> correctMultipleChoiceAnswers = new ArrayList<>();
    private String userSingleChoiceAnswer;
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

    public List<String> getCorrectMultipleChoiceAnswers() {
        return correctMultipleChoiceAnswers;
    }

    public void setCorrectMultipleChoiceAnswers(List<String> correctMultipleChoiceAnswers) {
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

    public String getUserSingleChoiceAnswer() {
        return userSingleChoiceAnswer;
    }

    public void setUserSingleChoiceAnswer(String userSingleChoiceAnswer) {
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

    public String getCorrectSingleChoiceAnswer() {
        return correctSingleChoiceAnswer;
    }

    public void setCorrectSingleChoiceAnswer(String correctSingleChoiceAnswer) {
        this.correctSingleChoiceAnswer = correctSingleChoiceAnswer;
    }
}
