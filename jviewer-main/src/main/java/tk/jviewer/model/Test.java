package tk.jviewer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Test representation.
 *
 * @author Evgeny Mironenko
 */
public class Test implements Serializable {

    private static final long serialVersionUID = 3480658726164313166L;

    private final String name;
    private final List<Question> questions;

    private int currentQuestionIndex;

    public Test(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public boolean isCurrentQuestionTheLast() {
        return currentQuestionIndex == questions.size() - 1;
    }

    public boolean isCurrentQuestionTheFirst() {
        return currentQuestionIndex == 0;
    }

    public void nextQuestion() {
        if (!isCurrentQuestionTheLast()) {
            currentQuestionIndex++;
        }
    }

    public void previousQuestion() {
        if (currentQuestionIndex != 0) {
            currentQuestionIndex--;
        }
    }

    public int getCorrectlyAnsweredQuestionsNumber() {
        int number = 0;
        for (final Question question : questions) {
            if (question.isCorrectlyAnswered()) {
                number++;
            }
        }

        return number;
    }

    public int getTotalQuestionsNumber() {
        return questions.size();
    }

    public Integer getProgress() {
        return (int) (getPassedQuestionsPortion() * 100);
    }

    //
    // Helper Methods
    //

    private double getPassedQuestionsPortion() {
        return (double) currentQuestionIndex / getTotalQuestionsNumber();
    }

}
