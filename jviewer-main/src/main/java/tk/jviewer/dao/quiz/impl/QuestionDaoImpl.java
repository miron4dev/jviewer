package tk.jviewer.dao.quiz.impl;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.quiz.AnswerDao;
import tk.jviewer.dao.quiz.QuestionDao;
import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;
import tk.jviewer.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

/**
 * See {@link QuestionDao}.
 */
public class QuestionDaoImpl implements QuestionDao {

    private int currentId = 0;
    private List<Question> questions = new ArrayList<>();

    @Override
    public Question findQuestion(Integer id) {
        for (Question q : questions) {
            if (q.getId().equals(id)) {
                return q;
            }
        }

        throw new RuntimeException("No question with id " + id);
    }

    @Override
    public void updateQuestion(final Question question) {
        final Question existing = findQuestion(question.getId());
        existing.setText(question.getText());
        existing.setPossibleAnswers(question.getPossibleAnswers());
        existing.setTypeOfAnswers(question.getTypeOfAnswers());
    }

    @Override
    public void removeQuestion(final Question question) {
        questions.remove(question);
    }

    @Override
    public Question createQuestion(final Integer quizId, final String text, final AnswerType answersType, final String correctTextualAnswer) {
        final Question question = new Question(++currentId, text, answersType);
        questions.add(question);
        return question;
    }

}
