package tk.jviewer.model.quiz;

/**
 * Created by Sergey Yaskov on 06.10.2015.
 */
public class WriteTextQuestion extends AbstractQuestion<String> {

    private static final long serialVersionUID = 6593349435654622666L;

    @Override
    public boolean isCorrect(final String answer) {
        return correctAnswer.equals(answer);
    }

}
