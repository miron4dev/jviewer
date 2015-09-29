package tk.jviewer.model;

import java.io.Serializable;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Test representation.
 *
 * @author Evgeny Mironenko
 */
public class Test implements Serializable {

    private static final long serialVersionUID = 3480658726164313166L;

    private final String name;
    private final List<Question> questions;
    private final int questionsToAnswerToPassTheTest;

    private int currentQuestionIndex;

    public Test(final String name, final List<Question> questions, final int questionsToAnswerToPassTheTest) {
        this.name = name;
        this.questions = questions;
        this.questionsToAnswerToPassTheTest = questionsToAnswerToPassTheTest;
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

    public List<Question> getQuestions() {
        return unmodifiableList(questions);
    }

    public void addQuestion(Question question) {
        questions.add(question);
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

    public boolean isPassed() {
        return getCorrectlyAnsweredQuestionsNumber() >= questionsToAnswerToPassTheTest;
    }

    //
    // Helper Methods
    //

    private double getPassedQuestionsPortion() {
        return (double) currentQuestionIndex / getTotalQuestionsNumber();
    }

}
