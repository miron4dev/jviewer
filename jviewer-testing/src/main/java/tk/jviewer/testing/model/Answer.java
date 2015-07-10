package tk.jviewer.testing.model;

import java.io.Serializable;

/**
 * Answer representation.
 * @author Evgeny Mironenko
 */
public class Answer implements Serializable {

    private static final long serialVersionUID = -1283565263146494192L;

    private String text;
    private boolean correct;
    private AnswerType type;
    // It should be filled only in case of AnswerType#TEXT_FIELD
    private String correctAnswer;

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

    public AnswerType getType() {
        return type;
    }

    public void setType(AnswerType type) {
        this.type = type;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
