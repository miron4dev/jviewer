package tk.jviewer.model.quiz;

import tk.jviewer.model.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;

/**
 * Created by Sergey Yaskov on 06.10.2015.
 */
public class MultipleChoiceQuestion extends AbstractQuestion<List<Answer>> {

    private List<Answer> answers = new ArrayList<>();

    @Override
    public boolean isCorrect(final List<Answer> answer) {
        return isEqualCollection(correctAnswer, answer);
    }

}
