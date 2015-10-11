package tk.jviewer.dialog;

import org.apache.log4j.Logger;
import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;
import tk.jviewer.model.Question;
import tk.jviewer.model.QuizResult;
import tk.jviewer.model.TakeQuizManagedBean;
import tk.jviewer.service.QuizService;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static tk.jviewer.model.Answer.createTextualAnswer;
import static tk.jviewer.model.AnswerType.CHECK_BOX;
import static tk.jviewer.model.AnswerType.RADIO_BUTTON;
import static tk.jviewer.model.AnswerType.TEXT_FIELD;

/**
 * Take Quiz dialog implementation.
 */
public class TakeQuizDialog implements Serializable {

    private static final long serialVersionUID = -2168142995752023300L;

    private static final Logger logger = Logger.getLogger(TakeQuizDialog.class);

    private TakeQuizManagedBean managedBean;

    private QuizService quizService;
    private Integer userSingleChoiceAnswer;
    private List<Integer> userMultipleChoiceAnswers;
    private String userTextualAnswer;

    private QuizProgressState chosenQuiz;

    @PostConstruct
    public void init() {
        chosenQuiz = new QuizProgressState(managedBean.getChosenQuiz());
    }

    public QuizProgressState getChosenQuiz() {
        return chosenQuiz;
    }

    public Question getCurrentQuestion() {
        final QuizProgressState chosenQuiz = getChosenQuiz();
        return chosenQuiz.getCurrentQuestion();
    }

    public boolean isFirstQuestion() {
        final QuizProgressState chosenQuiz = getChosenQuiz();
        return chosenQuiz.isCurrentQuestionTheFirst();
    }

    public boolean isLastQuestion() {
        final QuizProgressState chosenQuiz = getChosenQuiz();
        return chosenQuiz.isCurrentQuestionTheLast();
    }

    public void previousQuestion() {
        saveUserAnswers();
        getChosenQuiz().previousQuestion();
    }

    public void nextQuestion() {
        saveUserAnswers();
        getChosenQuiz().nextQuestion();
    }

    public String redirectToResults() {
        saveUserAnswers();

        final QuizProgressState quiz = getChosenQuiz();
        managedBean.setQuizResults(quizService.createQuizResult(quiz.getQuestions()));
        logger.debug("Going to redirect to results page");

        return "testresults?faces-redirect=true";
    }

    public List<QuizResult> getQuizResults() {
        return managedBean.getQuizResults();
    }

    public String cancelTest() {
        return "main?faces-redirect=true";
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

    public Integer getUserSingleChoiceAnswer() {
        return userSingleChoiceAnswer;
    }

    public void setUserSingleChoiceAnswer(Integer userSingleChoiceAnswer) {
        this.userSingleChoiceAnswer = userSingleChoiceAnswer;
    }

    public void setUserMultipleChoiceAnswers(List<Integer> userMultipleChoiceAnswers) {
        this.userMultipleChoiceAnswers = userMultipleChoiceAnswers;
    }

    public List<Integer> getUserMultipleChoiceAnswers() {
        return userMultipleChoiceAnswers;
    }

    public void setUserTextualAnswer(String userTextualAnswer) {
        this.userTextualAnswer = userTextualAnswer;
    }

    public String getUserTextualAnswer() {
        return userTextualAnswer;
    }

    //
    // Helper Methods
    //

    private void saveUserAnswers() {
        final Question currentQuestion = getChosenQuiz().getCurrentQuestion();
        currentQuestion.setUserAnswers(getChosenAnswers());
        quizService.updateQuestion(currentQuestion);
    }

    private List<Answer> getChosenAnswers() {
        final Question currentQuestion = getChosenQuiz().getCurrentQuestion();
        final List<Answer> possibleAnswers = currentQuestion.getPossibleAnswers();
        final AnswerType typeOfAnswers = currentQuestion.getTypeOfAnswers();

        if (typeOfAnswers == TEXT_FIELD) {
            return singletonList(createTextualAnswer(userTextualAnswer));
        } else if (typeOfAnswers == RADIO_BUTTON) {
            return lookupByIds(possibleAnswers, singletonList(userSingleChoiceAnswer));
        } else if (typeOfAnswers == CHECK_BOX) {
            return lookupByIds(possibleAnswers, userMultipleChoiceAnswers);
        } else {
            throw new IllegalStateException("Unknown answers type " + typeOfAnswers);
        }
    }

    private static List<Answer> lookupByIds(final List<Answer> answers, final List<Integer> ids) {
        final List<Answer> chosenAnswers = new ArrayList<>(ids.size());
        chosenAnswers.addAll(answers.stream().filter(answer -> ids.contains(answer.getId())).collect(toList()));
        return chosenAnswers;
    }

}
