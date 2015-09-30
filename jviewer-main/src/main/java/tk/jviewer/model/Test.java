package tk.jviewer.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Test representation.
 *
 * @author Evgeny Mironenko
 */
public class Test implements Serializable {

    private static final long serialVersionUID = 3480658726164313166L;

    private final long id;
    private String name;
    private final List<Question> questions;
    private int questionsToAnswerToPassTheTest;

    private int currentQuestionIndex;

    public Test(final long id, final String name, final int questionsToAnswerToPassTheTest) {
        this(id, name, new ArrayList<>(), questionsToAnswerToPassTheTest);
    }

    public Test(final long id, final String name, final List<Question> questions, final int questionsToAnswerToPassTheTest) {
        this.id = id;
        this.name = name;
        this.questions = questions;
        this.questionsToAnswerToPassTheTest = questionsToAnswerToPassTheTest;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void removeQuestion(Question question) {
        questions.remove(question);
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

    public int getQuestionsToAnswerToPassTheTest() {
        return questionsToAnswerToPassTheTest;
    }

    public void setQuestionsToAnswerToPassTheTest(int questionsToAnswerToPassTheTest) {
        this.questionsToAnswerToPassTheTest = questionsToAnswerToPassTheTest;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        return new EqualsBuilder()
                .append(id, test.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    //
    // Helper Methods
    //

    private double getPassedQuestionsPortion() {
        return (double) currentQuestionIndex / getTotalQuestionsNumber();
    }

}
