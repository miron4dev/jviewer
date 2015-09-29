package tk.jviewer.dialog;

import org.apache.log4j.Logger;
import tk.jviewer.converter.TestConverter;
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

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static tk.jviewer.model.AnswerType.CHECK_BOX;
import static tk.jviewer.model.AnswerType.RADIO_BUTTON;
import static tk.jviewer.model.AnswerType.TEXT_FIELD;

/**
 * Take Test dialog implementation.
 */
public class TakeTestDialog implements Serializable {

    private static final long serialVersionUID = -2168142995752023300L;

    private static final Logger logger = Logger.getLogger(TakeTestDialog.class);

    private static final String IDS_OF_ANSWERS_PARAM = "idsOfAnswers";

    private static final String TEXTUAL_ANSWER_PARAM = "textualAnswer";

    private static final String IDS_OF_ANSWERS_SEPARATOR = ",";

    private TakeTestManagedBean managedBean;

    private UserProfile userProfile;

    private QuizService quizService;

    /**
     * Look ups available tests for the current user.
     */
    @PostConstruct
    public void lookupAvailableTests() {
        List<Test> availableTests = singletonList(quizService.getQuiz());
        managedBean.setAvailableTests(availableTests);
        logger.info("Found " + availableTests.size() + " available tests for user " + userProfile.getName());
    }

    public List<Test> getAvailableTests() {
        return managedBean.getAvailableTests();
    }

    public Test getChosenTest() {
        return managedBean.getChosenTest();
    }

    public Question getCurrentQuestion() {
        Test chosenTest = getChosenTest();
        return chosenTest.getCurrentQuestion();
    }

    public boolean isFirstQuestion() {
        Test chosenTest = getChosenTest();
        return chosenTest.isCurrentQuestionTheFirst();
    }

    public boolean isLastQuestion() {
        Test chosenTest = getChosenTest();
        return chosenTest.isCurrentQuestionTheLast();
    }

    public void nextQuestion() {
        getChosenTest().nextQuestion();
    }

    public String redirectToResults() {
        return "testresults?faces-redirect=true";
    }

    public void previousQuestion() {
        getChosenTest().previousQuestion();
    }

    public String cancelTest() {
        return "testing?faces-redirect=true";
    }

    public void setChosenTest(Test chosenTest) {
        managedBean.setChosenTest(chosenTest);
    }

    public void saveAnswer() {
        final FacesContext context = FacesContext.getCurrentInstance();
        final Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        final String idsOfAnswers = params.get(IDS_OF_ANSWERS_PARAM);
        final String textualAnswer = params.get(TEXTUAL_ANSWER_PARAM);

        final Question currentQuestion = getCurrentQuestion();

        if (isNotBlank(idsOfAnswers)) {
            final String[] ids = idsOfAnswers.split(IDS_OF_ANSWERS_SEPARATOR);
            final List<String> userAnswers = new ArrayList<>(ids.length);
            Collections.addAll(userAnswers, ids);
            currentQuestion.setUserAnswers(userAnswers); // stupid JSF does not support result of Arrays.asList() during rendering
        } else if (isNotBlank(textualAnswer)) {
            currentQuestion.setUserTextualAnswer(textualAnswer);
        }
    }

    //
    // Dependency Injection
    //

    public void setManagedBean(TakeTestManagedBean managedBean) {
        this.managedBean = managedBean;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

}
