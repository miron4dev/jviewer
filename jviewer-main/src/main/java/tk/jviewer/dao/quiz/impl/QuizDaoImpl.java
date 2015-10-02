package tk.jviewer.dao.quiz.impl;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.quiz.QuestionDao;
import tk.jviewer.dao.quiz.QuizDao;
import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;
import tk.jviewer.model.Question;
import tk.jviewer.model.Quiz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static tk.jviewer.model.AnswerType.valueOf;

/**
 * See {@link QuizDao}.
 */
public class QuizDaoImpl extends JdbcDaoSupport implements QuizDao {

    private static final String SQL_FIND_QUIZZES =
            "SELECT " +
                    "  quiz.id     quiz_id, " +
                    "  name, " +
                    "  questions_to_answer_to_pass, " +
                    "  question.id   question_id, " +
                    "  question.text question_text, " +
                    "  answers_type, " +
                    "  correct_textual_answer, " +
                    "  answer.id     answer_id, " +
                    "  answer.text   answer_text, " +
                    "  correct " +
                    "FROM quiz " +
                    "  LEFT JOIN question ON question.quiz_id = quiz.id " +
                    "  LEFT JOIN answer ON answer.question_id = question.id";

    private static final String SQL_UPDATE_QUIZ =
            "update quiz set name = ?, questions_to_answer_to_pass = ? where id = ?";

    private static final String SQL_DELETE_QUIZ = "delete from quiz where id = ?";

    private QuestionDao questionDao;

    private static class QuizToRowMapper implements ResultSetExtractor<List<Quiz>> {

        @Override
        public List<Quiz> extractData(final ResultSet rs) throws SQLException {
            final Map<Long, Quiz> quizById = new HashMap<>();
            final Map<Long, Question> questionById = new HashMap<>();
            final Map<Long, Answer> answerById = new HashMap<>();
            while (rs.next()) {
                final long quizId = rs.getLong("quiz_id");
                final String name = rs.getString("name");
                final int questionsToAnswerToPass = rs.getInt("questions_to_answer_to_pass");
                Quiz quiz = quizById.get(quizId);
                if (quiz == null) {
                    quiz = new Quiz(quizId, name, questionsToAnswerToPass);
                    quizById.put(quizId, quiz);
                }

                final long questionId = rs.getLong("question_id");
                final String questionText = rs.getString("question_text");
                final AnswerType answersType = valueOf(rs.getString("answers_type"));
                final String correctTextualAnswer = rs.getString("correct_textual_answer");
                Question question = questionById.get(questionId);
                if (question == null) {
                    question = new Question(questionId, questionText, answersType, correctTextualAnswer);
                    questionById.put(questionId, question);
                    quiz.addQuestion(question);
                }

                final long answerId = rs.getLong("answer_id");
                final String answerTest = rs.getString("answer_text");
                final boolean answerCorrect = rs.getBoolean("correct");
                Answer answer = answerById.get(answerId);
                if (answer == null) {
                    answer = new Answer(answerId, answerTest, answerCorrect);
                    answerById.put(answerId, answer);
                    question.addAnswer(answer);
                }
            }

            return new ArrayList<>(quizById.values());
        }

    }

    @Override
    public long createQuiz(final String name, final int questionsToAnswerToPass) {
        final Number id = new SimpleJdbcInsert(getJdbcTemplate())
                .withTableName("quiz")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(of("name", name, "questions_to_answer_to_pass", questionsToAnswerToPass));

        return id.longValue();
    }

    /**
     * Finds quiz and all its questions with their answers. Makes one query to a database.
     */
    @Override
    public List<Quiz> findQuizzes() {
        return getJdbcTemplate().query(SQL_FIND_QUIZZES, new QuizToRowMapper());
    }

    @Override
    public void updateQuiz(final Quiz quiz) {
        getJdbcTemplate().update(SQL_UPDATE_QUIZ,
                quiz.getName(), quiz.getQuestionsToAnswerToPassTheTest(), quiz.getId());
    }

    @Override
    public void removeQuiz(final Quiz quiz) {
        getJdbcTemplate().update(SQL_DELETE_QUIZ, quiz.getId());
        for (final Question question : quiz.getQuestions()) {
            questionDao.removeQuestion(question);
        }
    }

    //
    // Dependency Injection
    //

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

}
