package tk.jviewer.dao.quiz;

import tk.jviewer.model.Question;
import tk.jviewer.model.Result;
import tk.jviewer.model.Test;

import java.util.List;

/**
 * Quiz Data Access Object interface.
 *
 * @author Evgeny Mironenko
 */
public interface QuizDao {

    long createQuiz(String name, int questionsToAnswerToPass);

    /**
     * Finds all quizzes.
     *
     * @return the list of the quizzes, not null.
     */
    List<Test> findQuizzes();

    void updateQuiz(Test quiz);

    void removeQuiz(Test quiz);

}
