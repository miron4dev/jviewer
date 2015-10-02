package tk.jviewer.service.impl;

import org.apache.commons.lang3.StringUtils;
import tk.jviewer.converter.TestConverter;
import tk.jviewer.dao.quiz.AnswerDao;
import tk.jviewer.dao.quiz.QuestionDao;
import tk.jviewer.dao.quiz.QuizDao;
import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;
import tk.jviewer.model.Question;
import tk.jviewer.model.Test;
import tk.jviewer.service.QuizService;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static tk.jviewer.model.AnswerType.CHECK_BOX;
import static tk.jviewer.model.AnswerType.RADIO_BUTTON;
import static tk.jviewer.model.AnswerType.TEXT_FIELD;

/**
 * See {@link QuizService}.
 */
public class QuizServiceImpl implements QuizService {

    private static final String DEFAULT_QUIZ_NAME = "New quiz";

    private static final int DEFAULT_QUESTIONS_TO_ANSWER_TO_PASS = 0;

    private static final String DEFAULT_QUESTION_TEXT = "Who lives in a pineapple under the sea?";

    private static final AnswerType DEFAULT_ANSWERS_TYPE = RADIO_BUTTON;

    private QuizDao quizDao;
    private QuestionDao questionDao;
    private AnswerDao answerDao;

    @Override
    public Test createQuiz() {
        final long quizId = quizDao.createQuiz(DEFAULT_QUIZ_NAME, DEFAULT_QUESTIONS_TO_ANSWER_TO_PASS);
        final Question question = new Question(DEFAULT_QUESTION_TEXT, DEFAULT_ANSWERS_TYPE);
        final long questionId = questionDao.createQuestion(quizId, DEFAULT_QUESTION_TEXT, DEFAULT_ANSWERS_TYPE, EMPTY);
        question.setId(questionId);

        return new Test(quizId, DEFAULT_QUIZ_NAME, new ArrayList<>(singletonList(question)), DEFAULT_QUESTIONS_TO_ANSWER_TO_PASS);
    }

    @Override
    public List<Test> findQuizzes() {
        return quizDao.findQuizzes();
    }

    @Override
    public void updateQuiz(final Test quiz) {
        quizDao.updateQuiz(quiz);
    }

    @Override
    public Question findQuestion(final long id) {
        return questionDao.findQuestion(id);
    }

    @Override
    public void createQuestion(final Test quiz, final String text) {
        final Question question = new Question(text, RADIO_BUTTON);
        final long id = questionDao.createQuestion(quiz.getId(), question.getText(), question.getTypeOfAnswers(), EMPTY);
        question.setId(id);
        quiz.addQuestion(question);
    }

    @Override
    public void createAnswer(final Question question, final Answer answer) {
        long id = answerDao.createAnswer(question.getId(), answer.getText(), answer.isCorrect());
        answer.setId(id);
        question.addAnswer(answer);
    }

    @Override
    public void removeAnswer(final Question question, final Answer answer) {
        answerDao.removeAnswer(answer);
        question.removeAnswer(answer);
    }

    @Override
    public void updateQuestion(final Question question) {
        questionDao.updateQuestion(question);
        for (final Answer answer : question.getAnswers()) {
            answerDao.updateAnswer(answer);
        }
    }

    @Override
    public void removeQuestion(final Test quiz, final Question question) {
        questionDao.removeQuestion(question);
        quiz.removeQuestion(question);
    }

    @Override
    public void removeQuiz(final Test quiz) {
        quizDao.removeQuiz(quiz);
    }

    //
    // Dependency Injection
    //

    public void setQuizDao(QuizDao quizDao) {
        this.quizDao = quizDao;
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public void setAnswerDao(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

}
