package tk.jviewer.dialog;

import org.apache.log4j.Logger;
import tk.jviewer.model.*;
import tk.jviewer.profile.UserProfile;
import tk.jviewer.service.QuizService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.Integer.getInteger;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Take Quiz dialog implementation.
 */
public class TakeQuizDialog implements Serializable {

    private static final long serialVersionUID = -2168142995752023300L;

    private static final Logger logger = Logger.getLogger(TakeQuizDialog.class);

    private static final String IDS_OF_ANSWERS_PARAM = "idsOfAnswers";

    private static final String TEXTUAL_ANSWER_PARAM = "textualAnswer";

    private static final String IDS_OF_ANSWERS_SEPARATOR = ",";

    private TakeQuizManagedBean managedBean;

    private QuizService quizService;

    public Quiz getChosenQuiz() {
        return managedBean.getChosenQuiz();
    }

    public void setChosenQuiz(Quiz quiz) {
        managedBean.setChosenQuiz(quiz);
    }

    public Question getCurrentQuestion() {
        final Quiz chosenQuiz = getChosenQuiz();
        return chosenQuiz.getCurrentQuestion();
    }

    public boolean isFirstQuestion() {
        final Quiz chosenQuiz = getChosenQuiz();
        return chosenQuiz.isCurrentQuestionTheFirst();
    }

    public boolean isLastQuestion() {
        final Quiz chosenQuiz = getChosenQuiz();
        return chosenQuiz.isCurrentQuestionTheLast();
    }

    public void nextQuestion() {
        getChosenQuiz().nextQuestion();
    }

    public String redirectToResults() {
        final Quiz quiz = getChosenQuiz();
        quizService.createQuizResult(quiz);

        return "testresults?faces-redirect=true";
    }

    public List<Map.Entry<Question, String>> getQuizResults() {
        final Set<Map.Entry<Question, String>> entries = getChosenQuiz().getResult().getUserAnswers().entrySet();
        return new ArrayList<>(entries);
    }

    public void previousQuestion() {
        getChosenQuiz().previousQuestion();
    }

    public String cancelTest() {
        return "main?faces-redirect=true";
    }

    public void saveAnswer() {
        final FacesContext context = FacesContext.getCurrentInstance();
        final Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        final String idsOfAnswers = params.get(IDS_OF_ANSWERS_PARAM);
        final String textualAnswer = params.get(TEXTUAL_ANSWER_PARAM);

        final Question currentQuestion = getCurrentQuestion();

        if (isNotBlank(idsOfAnswers)) {
            final String[] ids = idsOfAnswers.split(IDS_OF_ANSWERS_SEPARATOR);
            final List<Integer> userAnswers = new ArrayList<>(ids.length);
            for (final String id : ids) {
                userAnswers.add(getInteger(id));
            }
            currentQuestion.setUserMultipleChoiceAnswers(userAnswers);
        } else if (isNotBlank(textualAnswer)) {
            currentQuestion.setUserTextualAnswer(textualAnswer);
        }
    }

    //
    // Dependency Injection
    //

    public void setManagedBean(TakeQuizManagedBean managedBean) {
        this.managedBean = managedBean;
    }

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

}
