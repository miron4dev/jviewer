package tk.jviewer.model.quiz;

/**
 * Created by Sergey Yaskov on 06.10.2015.
 */
public abstract class AbstractQuestion<T> {

    protected Integer id;
    protected String text;
    protected T correctAnswer;

    public abstract boolean isCorrect(T answer);

}
