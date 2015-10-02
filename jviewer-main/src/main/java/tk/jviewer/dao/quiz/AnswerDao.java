package tk.jviewer.dao.quiz;

import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;

import java.util.List;

/**
 * Created by Sergey Yaskov on 30.09.2015.
 */
public interface AnswerDao {

    List<Answer> findAnswers(Long questionId);

    long createAnswer(long questionId, final String text, final boolean correct);

    void removeAnswer(Answer answer);

    void updateAnswer(Answer answer);

}
