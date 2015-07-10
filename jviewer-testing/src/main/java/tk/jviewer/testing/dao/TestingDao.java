package tk.jviewer.testing.dao;

import tk.jviewer.testing.model.Question;
import tk.jviewer.testing.model.Result;

import java.util.List;

/**
 * Testing Data Access Object interface.
 * @author Evgeny Mironenko
 */
public interface TestingDao {

    List<Question> getQuestions(String topic);

    List<Result> getResults(String userName);

}
