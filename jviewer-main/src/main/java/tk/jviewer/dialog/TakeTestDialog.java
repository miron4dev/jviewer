package tk.jviewer.dialog;

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

    private UserModel userModel;

    /**
     * Look ups available tests for the current user.
     */
    @PostConstruct
    public void lookupAvailableTests() {
        userModel.setAvailableTests(Collections.singletonList(fillDummyTest()));
    }

    /**
     * Returns dummy test. It should be removed after real implementation. Also please take care about {@link TestConverter}.
     * @return see description.
     */
    private Test fillDummyTest() {
        Question question = new Question();
        question.setId(1);
        question.setTopic("Test topic question");
        question.setText("Test text question");
        Answer answer1 = new Answer("The first answer", Boolean.FALSE.toString(), AnswerType.RADIO_BUTTON);
        Answer answer2 = new Answer("The second answer", Boolean.TRUE.toString(), AnswerType.RADIO_BUTTON);
        Answer answer3 = new Answer("The third answer", "correct", AnswerType.RADIO_BUTTON);
        question.setAnswers(Arrays.asList(answer1, answer2, answer3));
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        return new Test("Test", questions);
    }

    public List<Test> getAvailableTests() {
        return userModel.getAvailableTests();
    }

    public Test getChosenTest() {
        return userModel.getChosenTest();
    }

    public void setChosenTest(Test chosenTest) {
        userModel.setChosenTest(chosenTest);
    }

    //
    // Dependency Injection
    //

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
