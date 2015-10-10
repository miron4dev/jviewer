package tk.jviewer.service.impl;

import tk.jviewer.dao.quiz.AnswerDao;
import tk.jviewer.dao.quiz.QuestionDao;
import tk.jviewer.dao.quiz.QuizDao;
import tk.jviewer.dao.quiz.QuizResultDao;
import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;
import tk.jviewer.model.Question;
import tk.jviewer.model.Quiz;
import tk.jviewer.model.QuizResult;
import tk.jviewer.service.QuizService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;
import static tk.jviewer.model.Answer.lookupById;
import static tk.jviewer.model.AnswerType.CHECK_BOX;
import static tk.jviewer.model.AnswerType.RADIO_BUTTON;
import static tk.jviewer.model.AnswerType.TEXT_FIELD;

/**
 * See {@link QuizService}.
 */
public class QuizServiceImpl implements QuizService {

    private static final String DEFAULT_QUIZ_NAME = "New quiz";

    private static final int DEFAULT_QUESTIONS_TO_ANSWER_TO_PASS = 0;

    private QuizDao quizDao;
    private QuestionDao questionDao;
    private AnswerDao answerDao;
    private QuizResultDao quizResultDao;

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
    public QuizResult createQuizResult(final Quiz quiz) {
        final Map<Question, String> userAnswers = new HashMap<>();
        for (final Question question : quiz.getQuestions()) {
            userAnswers.put(question, formatUserAnswers(question));
        }
        final QuizResult quizResult = new QuizResult(userAnswers);
        quizResultDao.createQuizResult(quiz, quizResult);
        quiz.setResult(quizResult);
        return quizResult;
    }

    @Override
    public Question createQuestion(final Quiz quiz, final String text) {
        final Question question = questionDao.createQuestion(quiz.getId(), text, RADIO_BUTTON, EMPTY);
        quiz.addQuestion(question);
        return question;
    }

    @Override
    public void createAnswer(final Question question, final Answer answer) {
        answerDao.createAnswer(question.getId(), answer.getText(), answer.isCorrect());
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

    public void setQuizResultDao(QuizResultDao quizResultDao) {
        this.quizResultDao = quizResultDao;
    }

    //
    // Helper Methods
    //

    private static String formatUserAnswers(final Question question) {
        final StringBuilder builder = new StringBuilder();
        for (final Answer answer : question.getUserAnswers()) {
            builder.append(answer.getText()).append(", ");
        }

        return substringBeforeLast(builder.toString(), ", ");
    }

}
