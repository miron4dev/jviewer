package tk.jviewer.dao.quiz.impl;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.quiz.QuizDao;
import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;
import tk.jviewer.model.Question;
import tk.jviewer.model.Result;
import tk.jviewer.model.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static tk.jviewer.model.AnswerType.valueOf;

/**
 * Created by Sergey Yaskov on 30.09.2015.
 */
public class QuizDaoImpl extends JdbcDaoSupport implements QuizDao {

    private static final String QUIZ_SQL =
            "SELECT " +
                    "  quizzes.id     quiz_id, " +
                    "  name, " +
                    "  questions_to_answer_to_pass, " +
                    "  questions.id   question_id, " +
                    "  answers_type, " +
                    "  questions.text question_text, " +
                    "  answers.id     answer_id, " +
                    "  answers.text   answer_text, " +
                    "  correct " +
                    "FROM quizzes " +
                    "  LEFT JOIN questions ON questions.quiz_id = quizzes.id " +
                    "  LEFT JOIN answers ON answers.question_id = questions.id";

    private static class QuizToRowMapper implements ResultSetExtractor<List<Test>> {

        private final Map<Long, Test> quizById = new HashMap<>();
        private final Map<Long, Question> questionById = new HashMap<>();
        private final Map<Long, Answer> answerById = new HashMap<>();

        @Override
        public List<Test> extractData(final ResultSet rs) throws SQLException {
            while (rs.next()) {
                long quizId = rs.getLong("quiz_id");
                String name = rs.getString("name");
                int questionsToAnswerToPass = rs.getInt("questions_to_answer_to_pass");
                Test quiz = quizById.get(quizId);
                if (quiz == null) {
                    quiz = new Test(quizId, name, questionsToAnswerToPass);
                    quizById.put(quizId, quiz);
                }

                long questionId = rs.getLong("question_id");
                AnswerType answersType = AnswerType.valueOf(rs.getString("answers_type"));
                String questionText = rs.getString("question_text");
                Question question = questionById.get(questionId);
                if (question == null) {
                    question = new Question(questionId, questionText, answersType);
                    questionById.put(questionId, question);
                    quiz.addQuestion(question);
                }

                long answerId = rs.getLong("answer_id");
                String answerTest = rs.getString("answer_text");
                boolean answerCorrect = rs.getBoolean("correct");
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
                .withTableName("quizzes")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(of("name", name, "questions_to_answer_to_pass", questionsToAnswerToPass));

        return id.longValue();
    }

    @Override
    public void updateQuiz(final Test quiz) {
        getJdbcTemplate().update("update quizzes set name = ?, questions_to_answer_to_pass = ? where id = ?",
                quiz.getName(), quiz.getQuestionsToAnswerToPassTheTest(), quiz.getId());
    }

    @Override
    public List<Test> findQuizzes() {
        return getJdbcTemplate().query(QUIZ_SQL, new QuizToRowMapper());
    }

    @Override
    public void removeQuiz(final Test quiz) {
        getJdbcTemplate().update("delete from quizzes where id = ?", quiz.getId());
    }

}
