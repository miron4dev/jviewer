package tk.jviewer.service.impl;

import tk.jviewer.converter.TestConverter;
import tk.jviewer.model.Answer;
import tk.jviewer.model.Question;
import tk.jviewer.model.Test;
import tk.jviewer.service.QuizService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static tk.jviewer.model.AnswerType.CHECK_BOX;
import static tk.jviewer.model.AnswerType.RADIO_BUTTON;
import static tk.jviewer.model.AnswerType.TEXT_FIELD;

/**
 * For development purposes. Will be removed in the near future.
 */
public class DummyQuizService implements QuizService {

    private Test quiz;

    @PostConstruct
    public void init() {
        quiz = fillDummyTest();
    }

    @Override
    public Test getQuiz() {
        return quiz;
    }

    @Override
    public Question getQuestion(final long id) {
        for (Question question : quiz.getQuestions()) {
            if (question.getId() == id) {
                return question;
            }
        }

        throw new RuntimeException("Question with id " + id + " was not found");
    }

    @Override
    public void addQuestion(final Test quiz, final Question question) {
        question.setId(getUnusedId());
        quiz.addQuestion(question);
    }

    private long getUnusedId() {
        long id = 0;
        for (final Question question : quiz.getQuestions()) {
            id = max(id, question.getId());
        }

        return id + 1;
    }

    /**
     * Returns dummy test. It should be removed after real implementation. Also please take care about {@link TestConverter}.
     *
     * @return see description.
     */
    private Test fillDummyTest() {
        Question question1 = new Question(RADIO_BUTTON);
        question1.setId(1);
        question1.setTopic("To be or not to be?");
        question1.setText("That is the question");
        Answer answer1 = new Answer("0", "To be", RADIO_BUTTON);
        Answer answer2 = new Answer("1", "Not to be", RADIO_BUTTON);
        question1.setAnswers(new ArrayList<>(asList(answer1, answer2)));
        question1.setCorrectAnswers(new ArrayList<>(singletonList("0")));

        Question question2 = new Question(CHECK_BOX);
        question2.setId(2);
        question2.setTopic("Random test");
        question2.setText("Who lives in a pineapple under the sea?");
        Answer answer21 = new Answer("0", "Barmaley", CHECK_BOX);
        Answer answer22 = new Answer("1", "Sponge Bob Square Pants", CHECK_BOX);
        Answer answer23 = new Answer("2", "Earthworm Jim", CHECK_BOX);
        Answer answer24 = new Answer("3", "Princess Nesmeyana", CHECK_BOX);
        question2.setAnswers(new ArrayList<>(asList(answer21, answer22, answer23, answer24)));
        question2.setCorrectAnswers(new ArrayList<>(asList("1", "2")));

        Question question3 = new Question(TEXT_FIELD);
        question3.setId(3);
        question3.setTopic("Arithmetical question");
        question3.setText("2 + 2 = ?");
        question3.setCorrectTextualAnswer("4");

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        return new Test("Test", questions, 2);
    }

}
