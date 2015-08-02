package tk.jviewer.dialog;

import org.apache.log4j.Logger;
import tk.jviewer.converter.TestConverter;
import tk.jviewer.model.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Take Test dialog implementation.
 */
public class TakeTestDialog {

    private static final Logger LOGGER = Logger.getLogger(TakeTestDialog.class);

    private TakeTestManagedBean managedBean;
    private UserModel userModel;

    /**
     * Look ups available tests for the current user.
     */
    @PostConstruct
    public void lookupAvailableTests() {
        List<Test> availableTests = Collections.singletonList(fillDummyTest());
        managedBean.setAvailableTests(availableTests);
        LOGGER.info("Found " + availableTests.size() + " available tests for user " + userModel.getName());
    }

    /**
     * Returns dummy test. It should be removed after real implementation. Also please take care about {@link TestConverter}.
     * @return see description.
     */
    private Test fillDummyTest() {
        Question question1 = new Question();
        question1.setId(1);
        question1.setTopic("To be or not to be?");
        question1.setText("That is the question");
        Answer answer1 = new Answer("To be", Boolean.FALSE.toString(), AnswerType.RADIO_BUTTON);
        Answer answer2 = new Answer("Not to be", "correct", AnswerType.RADIO_BUTTON);
        question1.setAnswers(Arrays.asList(answer1, answer2));

        Question question2 = new Question();
        question2.setId(2);
        question2.setTopic("Random test");
        question2.setText("Who lives in a pineapple under the sea?");
        Answer answer21 = new Answer("Barmaley", Boolean.FALSE.toString(), AnswerType.CHECK_BOX);
        Answer answer22 = new Answer("Sponge Bob Square Pants", Boolean.TRUE.toString(), AnswerType.CHECK_BOX);
        Answer answer23 = new Answer("Earthworm Jim", "correct", AnswerType.CHECK_BOX);
        Answer answer24 = new Answer("Princess Nesmeyana", "correct", AnswerType.CHECK_BOX);
        question2.setAnswers(Arrays.asList(answer21, answer22, answer23, answer24));

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

    public void nextQuestion() {
        getChosenTest().nextQuestion();
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
