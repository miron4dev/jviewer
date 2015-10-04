package tk.jviewer.dao.quiz;

import tk.jviewer.model.Quiz;
import tk.jviewer.model.QuizResult;

/**
 * Created by Sergey Yaskov on 04.10.2015.
 */
public interface QuizResultDao {

    QuizResult createQuizResult(Quiz quiz, QuizResult quizResult);

}
