package tk.jviewer.service.impl;

import tk.jviewer.dao.quiz.AnswerDao;
import tk.jviewer.dao.quiz.QuestionDao;
import tk.jviewer.dao.quiz.QuizDao;
import tk.jviewer.model.Answer;
import tk.jviewer.model.Question;
import tk.jviewer.model.Quiz;
import tk.jviewer.model.QuizResult;
import tk.jviewer.service.QuizService;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;
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
    public List<QuizResult> createQuizResults(final List<Question> questions) {
        final List<QuizResult> results = new ArrayList<>(questions.size());
        for (final Question question : questions) {
            results.add(new QuizResult(question, formatCorrectAnswers(question), formatUserAnswers(question)));
        }
        return results;
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

    //
    // Helper Methods
    //

    private static String formatCorrectAnswers(final Question question) {
        final StringBuilder builder = new StringBuilder();
        for (final Answer answer : question.getPossibleAnswers()) {
            if (answer.isCorrect()) {
                builder.append(answer.getText()).append(", ");
            }
        }
        return substringBeforeLast(builder.toString(), ", ");
    }

    private static String formatUserAnswers(final Question question) {
        final StringBuilder builder = new StringBuilder();
        for (final Answer answer : question.getUserAnswers()) {
            builder.append(answer.getText()).append(", ");
        }

        return substringBeforeLast(builder.toString(), ", ");
    }

}
