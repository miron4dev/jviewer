package tk.jviewer.service.impl;

import tk.jviewer.dao.quiz.AnswerDao;
import tk.jviewer.dao.quiz.QuestionDao;
import tk.jviewer.dao.quiz.QuizDao;
import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;
import tk.jviewer.model.Question;
import tk.jviewer.model.Quiz;
import tk.jviewer.service.QuizService;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static tk.jviewer.model.AnswerType.RADIO_BUTTON;

/**
 * See {@link QuizService}.
 */
public class QuizServiceImpl implements QuizService {

    private static final String DEFAULT_QUIZ_NAME = "New quiz";

    private static final int DEFAULT_QUESTIONS_TO_ANSWER_TO_PASS = 0;

    private QuizDao quizDao;
    private QuestionDao questionDao;
    private AnswerDao answerDao;

    @Override
    public Quiz createQuiz() {
        return quizDao.createQuiz(DEFAULT_QUIZ_NAME, DEFAULT_QUESTIONS_TO_ANSWER_TO_PASS);
    }

    @Override
    public List<Quiz> findQuizzes() {
        return quizDao.findQuizzes();
    }

    @Override
    public void updateQuiz(final Quiz quiz) {
        quizDao.updateQuiz(quiz);
    }

    @Override
    public void removeQuiz(final Quiz quiz) {
        quizDao.removeQuiz(quiz);
    }

    @Override
    public Question createQuestion(final Quiz quiz, final String text) {
        final Question question = questionDao.createQuestion(quiz.getId(), text, RADIO_BUTTON, EMPTY);
        quiz.addQuestion(question);
        return question;
    }

    @Override
    public void createAnswer(final Question question, final Answer answer) {
        long id = answerDao.createAnswer(question.getId(), answer.getText(), answer.isCorrect());
        answer.setId(id);
        question.addAnswer(answer);
    }

    @Override
    public void updateQuestion(final Question question) {
        questionDao.updateQuestion(question);
    }

    @Override
    public void removeQuestion(final Quiz quiz, final Question question) {
        questionDao.removeQuestion(question);
        quiz.removeQuestion(question);
    }

    @Override
    public void removeAnswer(final Question question, final Answer answer) {
        answerDao.removeAnswer(answer);
        question.removeAnswer(answer);
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
