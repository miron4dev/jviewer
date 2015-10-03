package tk.jviewer.dao.quiz.impl;

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

    private static final String SQL_UPDATE_QUESTION =
            "update question set text = ?, answers_type = ?, correct_textual_answer = ? where id = ?";

    private static final String SQL_DELETE_QUESTION = "delete from question where id = ?";


    private AnswerDao answerDao;

    @Override
    public void updateQuestion(final Question question) {
        getJdbcTemplate().update(SQL_UPDATE_QUESTION, question.getText(),
                question.getTypeOfAnswers(), trimToEmpty(question.getCorrectTextualAnswer()), question.getId());
        for (final Answer answer : question.getAnswers()) {
            answerDao.updateAnswer(answer);
        }
    }

    @Override
    public void removeQuestion(final Question question) {
        getJdbcTemplate().update(SQL_DELETE_QUESTION, question.getId());
        for (final Answer answer : question.getAnswers()) {
            answerDao.removeAnswer(answer);
        }
    }

    @Override
    public Question createQuestion(final Integer quizId, final String text, final AnswerType answersType, final String correctTextualAnswer) {
        final Map<String, ?> params = of("quiz_id", quizId, "text", text, "answers_type", answersType,
                "correct_textual_answer", correctTextualAnswer);

        final Number id = new SimpleJdbcInsert(getJdbcTemplate())
                .withTableName("question")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(params);

        return new Question(id.intValue(), text, answersType, correctTextualAnswer);
    }

    //
    // Dependency Injection
    //

    public void setAnswerDao(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

}
