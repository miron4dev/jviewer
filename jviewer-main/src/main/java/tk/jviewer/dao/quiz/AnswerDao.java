package tk.jviewer.dao.quiz;

import tk.jviewer.model.AnswerType;

/**
 * Created by Sergey Yaskov on 30.09.2015.
 */
public interface AnswerDao {

    long createAnswer(long questionId, final String text);

    void removeAnswer(long id);

}
