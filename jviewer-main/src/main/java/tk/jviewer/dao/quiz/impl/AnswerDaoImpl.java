package tk.jviewer.dao.quiz.impl;

import tk.jviewer.dao.quiz.AnswerDao;
import tk.jviewer.dao.quiz.QuestionDao;
import tk.jviewer.model.Answer;
import tk.jviewer.model.Question;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.ImmutableMap.of;

/**
 * See {@link AnswerDao}.
 */
public class AnswerDaoImpl implements AnswerDao {

    private QuestionDao questionDao;

    private List<Answer> answers = new ArrayList<>();

    private int currentId = 0;

    @Override
    public Answer createAnswer(final Integer questionId, final String text, final boolean correct) {
        final Question question = questionDao.findQuestion(questionId);
        final Answer answer = new Answer(++currentId, text, correct);
        question.addPossibleAnswer(answer);
        answers.add(answer);
        return answer;
    }

    @Override
    public void updateAnswer(final Answer answer) {
        final Answer current = findAnswer(answer.getId());
        current.setText(answer.getText());
        current.setCorrect(current.isCorrect());
    }

    @Override
    public Answer findAnswer(Integer id) {
        for (Answer answer : answers) {
            if (id.equals(answer.getId())) {
                return answer;
            }
        }

        throw new RuntimeException("No answer with id " + id);
    }

    @Override
    public void removeAnswer(final Answer answer) {
        answers.remove(answer);
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

}
