package tk.jviewer.dao.quiz.impl;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.quiz.QuizDao;
import tk.jviewer.model.Question;
import tk.jviewer.model.Result;
import tk.jviewer.model.Test;

import java.util.List;

import static com.google.common.collect.ImmutableMap.of;
import static tk.jviewer.model.AnswerType.valueOf;

/**
 * Created by Sergey Yaskov on 30.09.2015.
 */
public class QuizDaoImpl extends JdbcDaoSupport implements QuizDao {

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
    public List<Question> getQuestions(String topic) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Result> getResults(String userName) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Test> findQuizzes() {
        return getJdbcTemplate().query("select id, name, questions_to_answer_to_pass from quizzes", (rs, rowNum) -> {
            return new Test(rs.getLong("id"), rs.getString("name"), rs.getInt("questions_to_answer_to_pass"));
        });
    }

}
