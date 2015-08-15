package tk.jviewer.model;

import com.google.common.collect.Iterables;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test representation.
 *
 * @author Evgeny Mironenko
 */
public class Test implements Serializable {

    private static final long serialVersionUID = 3480658726164313166L;

    private String name;
    private Map<Question, Boolean> questions;
    private int currentQuestionIndex;

    public Test(String name, List<Question> questions) {
        this.name = name;
        this.questions = new HashMap<>();
        for (Question question : questions) {
            this.questions.put(question, false);
        }
    }

    public String getName() {
        return name;
    }

    public Question getCurrentQuestion() {
        return Iterables.get(questions.keySet(), currentQuestionIndex);
    }

    public boolean isCurrentQuestionTheLast() {
        return currentQuestionIndex == questions.size() - 1;
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
        for (final Question question : questions.keySet()) {
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
