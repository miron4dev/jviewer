package tk.jviewer.dialog;

import org.springframework.util.Assert;
import tk.jviewer.model.Question;
import tk.jviewer.model.Quiz;

import java.util.List;

import static org.springframework.util.Assert.state;

/**
 * Created by Sergey Yaskov on 11.10.2015.
 */
public class QuizProgressState {

    private final Quiz quiz;
    private int currentQuestionIndex;

    public QuizProgressState(final Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Question> getQuestions() {
        return quiz.getQuestions();
    }

    public Question getCurrentQuestion() {
        final List<Question> questions = quiz.getQuestions();
        return questions.isEmpty() ? null : questions.get(currentQuestionIndex);
    }

    public boolean isCurrentQuestionTheLast() {
        return currentQuestionIndex == quiz.getQuestions().size() - 1;
    }

    public boolean isCurrentQuestionTheFirst() {
        return currentQuestionIndex == 0;
    }

    public void nextQuestion() {
        state(!isCurrentQuestionTheLast(), "Unexpected call, the current question is last");
        currentQuestionIndex++;
    }

    public void previousQuestion() {
        state(!isCurrentQuestionTheFirst(), "Unexpected call, the current question is first");
        currentQuestionIndex--;
    }

    public int getTotalQuestionsNumber() {
        return quiz.getQuestions().size();
    }

    public Integer getProgress() {
        return (int) (getPassedQuestionsPortion() * 100);
    }

    public int getCorrectlyAnsweredQuestionsNumber() {
        int number = 0;
        for (final Question question : quiz.getQuestions()) {
            if (question.isCorrectlyAnswered()) {
                number++;
            }
        }

        return number;
    }

    public boolean isSuccessfullyPassed() {
        return getCorrectlyAnsweredQuestionsNumber() >= quiz.getQuestionsToAnswerToPassTheTest();
    }

    //
    // Helper Methods
    //

    private double getPassedQuestionsPortion() {
        return (double) currentQuestionIndex / getTotalQuestionsNumber();
    }

}
