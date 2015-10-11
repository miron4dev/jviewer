package tk.jviewer.service;

import tk.jviewer.model.Answer;
import tk.jviewer.model.Question;
import tk.jviewer.model.Quiz;
import tk.jviewer.model.QuizResult;

import java.util.List;

/**
 * Created by Sergey Yaskov on 29.09.2015.
 */
public interface QuizService {

    Quiz createQuiz();

    List<Quiz> findQuizzes();

    void updateQuiz(Quiz quiz);

    Question createQuestion(Quiz quiz, String text);

    void createAnswer(Question question, Answer answer);

    void removeAnswer(Question question, Answer answer);

    void updateQuestion(Question question);

    void removeQuestion(Quiz quiz, Question question);

    void removeQuiz(Quiz quiz);

    List<QuizResult> createQuizResults(final List<Question> questions);

}
