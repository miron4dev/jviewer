package tk.jviewer.dao.quiz.impl;

import tk.jviewer.dao.quiz.QuizDao;
import tk.jviewer.model.Quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * See {@link QuizDao}.
 */
public class QuizDaoImpl implements QuizDao {

    private List<Quiz> quizzes = new ArrayList<>();
    private int currentId = 0;

    @Override
    public Quiz createQuiz(final String name, final int questionsToAnswerToPass) {
        final Quiz quiz = new Quiz(++currentId, name, questionsToAnswerToPass);
        quizzes.add(quiz);
        return quiz;
    }

    @Override
    public List<Quiz> findQuizzes() {
        return quizzes;
    }

    @Override
    public void updateQuiz(final Quiz quiz) {
        for (Quiz current : quizzes) {
            if (current.getId().equals(quiz.getId())) {
                current.setName(quiz.getName());
                current.setQuestionsToAnswerToPassTheTest(quiz.getQuestionsToAnswerToPassTheTest());
                return;
            }
        }

        throw new IllegalStateException("No such quiz " + quiz);
    }

    @Override
    public void removeQuiz(final Quiz quiz) {
        quizzes.remove(quiz);
    }

}
