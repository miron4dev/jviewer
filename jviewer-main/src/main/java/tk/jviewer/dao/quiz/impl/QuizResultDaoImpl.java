package tk.jviewer.dao.quiz.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.quiz.QuizResultDao;
import tk.jviewer.model.Quiz;
import tk.jviewer.model.QuizResult;

/**
 * See {@link QuizResultDao}.
 */
public class QuizResultDaoImpl extends JdbcDaoSupport implements QuizResultDao {

    @Override
    public QuizResult createQuizResult(final Quiz quiz, final QuizResult quizResult) {
        return null;
    }

}
