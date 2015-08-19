package tk.jviewer.model;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Contains Take Test dialog state.
 */
public class TakeTestManagedBean {

    private Test chosenTest;
    private List<Test> availableTests;

    public Test getChosenTest() {
        return chosenTest;
    }

    public void setChosenTest(Test chosenTest) {
        this.chosenTest = chosenTest;
    }

    public List<Test> getAvailableTests() {
        return availableTests;
    }

    public void setAvailableTests(List<Test> availableTests) {
        this.availableTests = availableTests;
    }

    public Test lookupTestByName(String name) {
        if (isEmpty(name)) {
            return null;
        }
        for (Test test: availableTests) {
            if (name.equals(test.getName())) {
                return test;
            }
        }
        return null;
    }
}
