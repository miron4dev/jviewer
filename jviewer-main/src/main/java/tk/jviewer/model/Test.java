package tk.jviewer.model;

import com.google.common.collect.Iterables;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test representation.
 * @author Evgeny Mironenko
 */
public class Test implements Serializable {

    private static final long serialVersionUID = 3480658726164313166L;

    private String name;
    private Map<Question, Boolean> questions;
    private int currentQuestionIndex;

    public Test(String name, List<Question> questions) {
        this.name = name;
        this.questions = new HashMap<>();
        for (Question question: questions) {
            this.questions.put(question, false);
        }
    }

    public String getName() {
        return name;
    }

    public Question getCurrentQuestion() {
        return Iterables.get(questions.keySet(), currentQuestionIndex);
    }

    public void nextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
        }
    }

    public void previousQuestion() {
        if (currentQuestionIndex != 0) {
            currentQuestionIndex--;
        }
    }

    public Integer getProgress() {
        return 0;
    }
}
