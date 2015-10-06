package tk.jviewer.model.quiz;

import tk.jviewer.model.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Yaskov on 06.10.2015.
 */
public class SingleChoiceQuestion extends AbstractQuestion<Answer> {

    private List<Answer> answers = new ArrayList<>();

    @Override
    public boolean isCorrect(final Answer answer) {
        return correctAnswer.equals(answer);
    }

}
