package tk.jviewer.dao.quiz.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.quiz.AnswerDao;
import tk.jviewer.dao.quiz.QuestionDao;
import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;
import tk.jviewer.model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import static tk.jviewer.model.AnswerType.valueOf;

/**
 * See {@link QuestionDao}.
 */
public class QuestionDaoImpl extends JdbcDaoSupport implements QuestionDao {

    private static final String SQL_FIND_QUESTION =
            "select id, text, answers_type, correct_textual_answer from question where id = ?";

    private static final String SQL_UPDATE_QUESTION =
            "update question set text = ?, answers_type = ?, correct_textual_answer = ? where id = ?";

    private static final String SQL_DELETE_QUESTION = "delete from question where id = ?";

    private static final RowMapper<Question> QUESTION_ROW_MAPPER = (rs, rowNum) -> fromResultSet(rs);

    private AnswerDao answerDao;

    /**
     * Finds question and all its answers, makes two queries: the first for question itself,
     * the second for all answers for this question.
     */
    @Override
    public Question findQuestion(final long id) {
        final Question question =
                getJdbcTemplate().queryForObject(SQL_FIND_QUESTION, new Object[]{id}, QUESTION_ROW_MAPPER);
        final List<Answer> answers = answerDao.findAnswers(question.getId());
        question.setAnswers(answers);
        return question;
    }

    @Override
    public void updateQuestion(final Question question) {
        getJdbcTemplate().update(SQL_UPDATE_QUESTION, question.getText(),
                question.getTypeOfAnswers(), trimToEmpty(question.getCorrectTextualAnswer()), question.getId());
    }

    @Override
    public void removeQuestion(final long id) {
        getJdbcTemplate().update(SQL_DELETE_QUESTION, id);
    }

    @Override
    public long createQuestion(final long quizId, final String text, final AnswerType answersType, final String correctTextualAnswer) {
        final Map<String, ?> params = of("quiz_id", quizId, "text", text, "answers_type", answersType,
                "correct_textual_answer", correctTextualAnswer);

        final Number id = new SimpleJdbcInsert(getJdbcTemplate())
                .withTableName("question")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(params);

        return id.longValue();
    }

    //
    // Dependency Injection
    //

    public void setAnswerDao(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

    //
    // Helper Methods
    //

    private static Question fromResultSet(final ResultSet rs) throws SQLException {
        return new Question(rs.getLong("id"), rs.getString("text"),
                valueOf(rs.getString("answers_type")), rs.getString("correct_textual_answer"));
    }

}
