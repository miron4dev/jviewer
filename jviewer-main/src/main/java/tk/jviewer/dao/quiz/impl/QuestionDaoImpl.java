package tk.jviewer.dao.quiz.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.quiz.QuestionDao;
import tk.jviewer.model.AnswerType;
import tk.jviewer.model.Question;

import java.util.List;

import static com.google.common.collect.ImmutableMap.of;
import static tk.jviewer.model.AnswerType.valueOf;

/**
 * Created by Sergey Yaskov on 30.09.2015.
 */
public class QuestionDaoImpl extends JdbcDaoSupport implements QuestionDao {

    private static final String SQL_FIND_QUESTION = "select id, answers_type, text from questions where id = ?";
    private static final String SQL_FIND_QUESTIONS = "select id, answers_type, text from questions where quiz_id = ?";

    private static final RowMapper<Question> QUESTION_ROW_MAPPER = (rs, rowNum) -> new Question(rs.getLong("id"), rs.getString("text"), valueOf(rs.getString("answers_type")));

    @Override
    public Question findQuestion(final long id) {
        return getJdbcTemplate().queryForObject(SQL_FIND_QUESTION, new Object[]{id}, QUESTION_ROW_MAPPER);
    }

    @Override
    public List<Question> findQuestions(long quizId) {
        return getJdbcTemplate().query(SQL_FIND_QUESTIONS, new Object[]{quizId}, QUESTION_ROW_MAPPER);
    }

    @Override
    public void updateQuestion(final Question question) {
        getJdbcTemplate().update("update questions set answers_type = ?, text = ? where id = ?",
                question.getTypeOfAnswers(), question.getText(), question.getId());
    }

    @Override
    public void removeQuestion(final long id) {
        getJdbcTemplate().update("delete from questions where id = ?", id);
    }

    @Override
    public long createQuestion(final long quizId, final AnswerType answersType, final String text) {
        final Number id = new SimpleJdbcInsert(getJdbcTemplate())
                .withTableName("questions")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(of("quiz_id", quizId, "answers_type", answersType, "text", text));

        return id.longValue();
    }

}
