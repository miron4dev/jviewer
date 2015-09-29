package tk.jviewer.service;

import tk.jviewer.model.Question;
import tk.jviewer.model.Test;

/**
 * Created by Sergey Yaskov on 29.09.2015.
 */
public interface QuizService {

    Test getQuiz();

    Question getQuestion(long id);
}
