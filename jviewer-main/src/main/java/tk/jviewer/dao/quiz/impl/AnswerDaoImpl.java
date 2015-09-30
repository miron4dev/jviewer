package tk.jviewer.dao.quiz.impl;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.quiz.AnswerDao;

import static com.google.common.collect.ImmutableMap.of;

/**
 * Created by Sergey Yaskov on 30.09.2015.
 */
public class AnswerDaoImpl extends JdbcDaoSupport implements AnswerDao {

    @Override
    public long createAnswer(final long questionId, final String text) {
        final Number id = new SimpleJdbcInsert(getJdbcTemplate())
                .withTableName("answers")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(of("question_id", questionId, "text", text));

        return id.longValue();
    }

    @Override
    public void removeAnswer(final long id) {
        getJdbcTemplate().update("delete from answers where id = ?", id);
    }

}
