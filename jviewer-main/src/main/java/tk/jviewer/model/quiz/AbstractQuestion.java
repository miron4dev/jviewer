package tk.jviewer.model.quiz;

import java.io.Serializable;

/**
 * Created by Sergey Yaskov on 06.10.2015.
 */
public abstract class AbstractQuestion<T> implements Serializable {

    private static final long serialVersionUID = 4217690855856844411L;

    protected Integer id;
    protected String text;
    protected T correctAnswer;

    public abstract boolean isCorrect(T answer);

}
