package tk.jviewer.dialog;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import tk.jviewer.converter.TestConverter;
import tk.jviewer.model.Answer;
import tk.jviewer.model.Question;
import tk.jviewer.model.TakeTestManagedBean;
import tk.jviewer.model.Test;
import tk.jviewer.model.UserModel;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static tk.jviewer.model.AnswerType.CHECK_BOX;
import static tk.jviewer.model.AnswerType.RADIO_BUTTON;

/**
 * Take Test dialog implementation.
 */
public class TakeTestDialog implements Serializable {

    private static final long serialVersionUID = -2168142995752023300L;

    private static final Logger logger = Logger.getLogger(TakeTestDialog.class);

    private static final String IDS_OF_ANSWERS_PARAM = "idsOfAnswers";

    private static final String IDS_OF_ANSWERS_SEPARATOR = ",";

    private TakeTestManagedBean managedBean;
    private UserModel userModel;

    /**
     * Look ups available tests for the current user.
     */
    @PostConstruct
    public void lookupAvailableTests() {
        List<Test> availableTests = singletonList(fillDummyTest());
        managedBean.setAvailableTests(availableTests);
        logger.info("Found " + availableTests.size() + " available tests for user " + userModel.getName());
    }

    /**
     * Returns dummy test. It should be removed after real implementation. Also please take care about {@link TestConverter}.
     *
     * @return see description.
     */
    private Test fillDummyTest() {
        Question question1 = new Question();
        question1.setId(1);
        question1.setTopic("To be or not to be?");
        question1.setText("That is the question");
        Answer answer1 = new Answer("0", "To be", Boolean.FALSE.toString(), RADIO_BUTTON);
        Answer answer2 = new Answer("1", "Not to be", "correct", RADIO_BUTTON);
        question1.setAnswers(asList(answer1, answer2));
        question1.setCorrectAnswers(singletonList("0"));
        question1.setTypeOfAnswers(RADIO_BUTTON);

        Question question2 = new Question();
        question2.setId(2);
        question2.setTopic("Random test");
        question2.setText("Who lives in a pineapple under the sea?");
        Answer answer21 = new Answer("0", "Barmaley", Boolean.FALSE.toString(), CHECK_BOX);
        Answer answer22 = new Answer("1", "Sponge Bob Square Pants", Boolean.TRUE.toString(), CHECK_BOX);
        Answer answer23 = new Answer("2", "Earthworm Jim", "correct", CHECK_BOX);
        Answer answer24 = new Answer("3", "Princess Nesmeyana", "correct", CHECK_BOX);
        question2.setAnswers(asList(answer21, answer22, answer23, answer24));
        question2.setCorrectAnswers(asList("1", "2"));
        question2.setTypeOfAnswers(CHECK_BOX);

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);

        return new Test("Test", questions);
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

        if (StringUtils.isBlank(idsOfAnswers)) {
            return;
        }

        final String[] ids = idsOfAnswers.split(IDS_OF_ANSWERS_SEPARATOR);

        final List<String> userAnswers = new ArrayList<>(ids.length);
        Collections.addAll(userAnswers, ids);
        getCurrentQuestion().setUserAnswers(userAnswers); // stupid JSF does not support result of Arrays.asList() during rendering
    }

    //
    // Dependency Injection
    //

    public void setManagedBean(TakeTestManagedBean managedBean) {
        this.managedBean = managedBean;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

}
