package tk.jviewer.dao.quiz;

import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;
import tk.jviewer.model.Question;

import java.util.List;

/**
 * Created by Sergey Yaskov on 30.09.2015.
 */
public interface QuestionDao {

    Question createQuestion(Integer quizId, String text, AnswerType answerType, String correctTextualAnswer);

    void updateQuestion(Question question);

    void removeQuestion(Question question);

}
