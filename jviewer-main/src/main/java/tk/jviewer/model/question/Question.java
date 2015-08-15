package tk.jviewer.model.question;

import org.apache.commons.lang3.StringUtils;
import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;

import java.io.Serializable;
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

    private int id;
    private String topic;
    private String text;
    private List<Answer> answers;
    private List<String> correctAnswers;
    private String userAnswer;
    private String correctTextualAnswer;
    private String userTextualAnswer;
    private List<String> userAnswers;
    private AnswerType typeOfAnswers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void setCorrectAnswers(List<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void setCorrectTextualAnswer(String correctTextualAnswer) {
        this.correctTextualAnswer = correctTextualAnswer;
    }

    public void setUserTextualAnswer(String userTextualAnswer) {
        this.userTextualAnswer = userTextualAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public List<String> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<String> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public boolean isCorrectlyAnswered() {
        if (typeOfAnswers == RADIO_BUTTON) {
            return correctAnswers.contains(userAnswer);
        } else if (typeOfAnswers == CHECK_BOX) {
            return isEqualCollection(correctAnswers, userAnswers);
        } else if (typeOfAnswers == TEXT_FIELD) {
            return correctTextualAnswer.equals(trim(userTextualAnswer));
        }

        throw new RuntimeException("Unsupported answers type " + typeOfAnswers);
    }

}
