package tk.jviewer.dao.quiz;

import tk.jviewer.model.Quiz;

import java.util.List;

/**
 * Quiz Data Access Object interface.
 *
 * @author Evgeny Mironenko
 */
public interface QuizDao {

    Quiz createQuiz(String name, int questionsToAnswerToPass);

    /**
     * Finds all quizzes.
     *
     * @return the list of the quizzes, not null.
     */
    List<Quiz> findQuizzes();

    void updateQuiz(Quiz quiz);

    void removeQuiz(Quiz quiz);

}
