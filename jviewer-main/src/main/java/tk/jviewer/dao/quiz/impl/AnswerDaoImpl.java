package tk.jviewer.dao.quiz.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.quiz.AnswerDao;
import tk.jviewer.model.Answer;

import java.util.List;

import static com.google.common.collect.ImmutableMap.of;

/**
 * Created by Sergey Yaskov on 30.09.2015.
 */
public class AnswerDaoImpl extends JdbcDaoSupport implements AnswerDao {

    private static final String SQL_FIND_ANSWERS = "select id, text, correct from answers where question_id = ?";

    private static final RowMapper<Answer> ANSWER_ROW_MAPPER =
            (rs, rowNum) -> new Answer(rs.getLong("id"), rs.getString("text"), rs.getBoolean("correct"));

    @Override
    public List<Answer> findAnswers(final Long questionId) {
        return getJdbcTemplate().query(SQL_FIND_ANSWERS, new Object[]{questionId}, ANSWER_ROW_MAPPER);
    }

    @Override
    public long createAnswer(final long questionId, final String text, final boolean correct) {
        final Number id = new SimpleJdbcInsert(getJdbcTemplate())
                .withTableName("answers")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(of("question_id", questionId, "text", text, "correct", correct));

        return id.longValue();
    }

    @Override
    public void updateAnswer(final Answer answer) {
        getJdbcTemplate().update("update answers set text = ?, correct = ? where id = ?",
                answer.getText(), answer.isCorrect(), answer.getId());
    }

    @Override
    public void removeAnswer(final long id) {
        getJdbcTemplate().update("delete from answers where id = ?", id);
    }

}
