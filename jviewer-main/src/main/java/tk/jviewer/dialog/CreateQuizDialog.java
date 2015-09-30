package tk.jviewer.dialog;

import tk.jviewer.model.Answer;
import tk.jviewer.model.AnswerType;
import tk.jviewer.model.Question;
import tk.jviewer.model.Test;
import tk.jviewer.model.ViewerManagedBean;
import tk.jviewer.service.QuizService;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

import static java.lang.Long.parseLong;
import static org.springframework.util.Assert.hasText;
import static tk.jviewer.model.AnswerType.RADIO_BUTTON;

/**
 * Serves "create quiz" use case.
 */
public class CreateQuizDialog implements Serializable {

    private static final long serialVersionUID = -2334735949960107078L;

    private QuizService quizService;

    private ViewerManagedBean viewerManagedBean; // TODO replace with separate one

    private Test quiz;

    private Question editingQuestion;

    private String newQuestionText;
    private String newAnswerText;

    @PostConstruct
    public void init() {
        final Test emptyQuiz = viewerManagedBean.getCurrentQuiz(); // TODO: refactor
        quiz = new Test(emptyQuiz.getId(), emptyQuiz.getName(), quizService.findQuestionsForQuiz(emptyQuiz.getId()), emptyQuiz.getQuestionsToAnswerToPassTheTest());
        editingQuestion = quiz.getCurrentQuestion();
    }

    public Test getQuiz() {
        return quiz;
    }

    public Question getEditingQuestion() {
        return editingQuestion;
    }

    public String getNewQuestionText() {
        return newQuestionText;
    }

    public void setNewQuestionText(String newQuestionText) {
        this.newQuestionText = newQuestionText;
    }

    public String getNewAnswerText() {
        return newAnswerText;
    }

    public void setNewAnswerText(String newAnswerText) {
        this.newAnswerText = newAnswerText;
    }

    public String cancelQuizCreation() {
        return "main?faces-redirect=true";
    }

    public void onEditingQuestionChanged() {
        final long id = getIdFromRequest();
        editingQuestion = quizService.findQuestion(id);
    }

    public void onAddNewQuestionPressed() {
        hasText(newQuestionText);
        quizService.createQuestion(quiz, newQuestionText);
    }

    public void onDeleteQuestionPressed() {
        final long id = getIdFromRequest();
        final Question question = quizService.findQuestion(id);
        quiz.removeQuestion(question);
    }

    public void onAddNewAnswerPressed() {
        hasText(newAnswerText);
        final Answer answer = new Answer(newAnswerText);
        quizService.createAnswer(editingQuestion, answer);
        final AnswerType typeOfAnswers = editingQuestion.getTypeOfAnswers();
        if (typeOfAnswers == RADIO_BUTTON && editingQuestion.getCorrectSingleChoiceAnswer() == null) {
            editingQuestion.setCorrectSingleChoiceAnswer(answer.getId());
        }
    }

    public void onDeleteAnswerPressed() {
        final long answerId = getIdFromRequest();
        quizService.removeAnswer(editingQuestion, answerId);
    }

    public void onQuizChanged() {
        quizService.updateQuiz(quiz);
    }

    //
    // Dependency Injection
    //

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    public void setViewerManagedBean(ViewerManagedBean viewerManagedBean) {
        this.viewerManagedBean = viewerManagedBean;
    }

    //
    // Helper Methods
    //

    private long getIdFromRequest() {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final ExternalContext externalContext = facesContext.getExternalContext();
        final Map<String, String> params = externalContext.getRequestParameterMap();

        return parseLong(params.get("id"));
    }

}
