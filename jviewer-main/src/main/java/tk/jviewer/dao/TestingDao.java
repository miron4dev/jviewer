package tk.jviewer.dao;

import tk.jviewer.model.Question;
import tk.jviewer.model.Result;

import java.util.List;

/**
 * Testing Data Access Object interface.
 * @author Evgeny Mironenko
 */
public interface TestingDao {

    List<Question> getQuestions(String topic);

    List<Result> getResults(String userName);

}
