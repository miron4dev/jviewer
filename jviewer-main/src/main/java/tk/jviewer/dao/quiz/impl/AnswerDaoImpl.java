package tk.jviewer.dao.quiz.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.quiz.AnswerDao;
import tk.jviewer.model.Answer;

import java.util.List;

import static com.google.common.collect.ImmutableMap.of;

/**
 * See {@link AnswerDao}.
 */
public class AnswerDaoImpl extends JdbcDaoSupport implements AnswerDao {

    private static final String SQL_FIND_ANSWERS_FOR_QUESTION =
            "select id, text, correct from answer where question_id = ?";

    private static final String SQL_UPDATE_ANSWER = "update answer set text = ?, correct = ? where id = ?";

    private static final String SQL_DELETE_ANSWER = "delete from answer where id = ?";

    private static final RowMapper<Answer> ANSWER_ROW_MAPPER =
            (rs, rowNum) -> new Answer(rs.getLong("id"), rs.getString("text"), rs.getBoolean("correct"));

    @Override
    public List<Answer> findAnswers(final Long questionId) {
        return getJdbcTemplate().query(SQL_FIND_ANSWERS_FOR_QUESTION, new Object[]{questionId}, ANSWER_ROW_MAPPER);
    }

    @Override
    public long createAnswer(final long questionId, final String text, final boolean correct) {
        final Number id = new SimpleJdbcInsert(getJdbcTemplate())
                .withTableName("answer")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(of("question_id", questionId, "text", text, "correct", correct));

        return id.longValue();
    }

    @Override
    public void updateAnswer(final Answer answer) {
        getJdbcTemplate().update(SQL_UPDATE_ANSWER, answer.getText(), answer.isCorrect(), answer.getId());
    }

    @Override
    public void removeAnswer(final long id) {
        getJdbcTemplate().update(SQL_DELETE_ANSWER, id);
    }

}
